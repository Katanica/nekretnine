import styles from "./css/Profile.module.css";
import { LogOut, Edit2 } from "lucide-react";
import { FiPhone, FiMapPin, FiCalendar } from "react-icons/fi";
import { Form, useParams, useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import { getToken, getUserID, getRole, getProfileType } from "../api";
import Property from "../components/Property";
import { formatDate } from "../formatDate";
import AdvertDetailsModal from "../components/AdvertDetailsModal";
import EditAdvertModal from "../components/EditAdvertModal";
import EditProfileModal from "../components/EditProfileModal";
import DeleteAdvertModal from "../components/DeleteAdvertModal";
import DeleteUserModal from "../components/DeleteUserModal";
export default function Profile() {
  const userID = getUserID();
  const role = getRole();
  const token = getToken();
  const navigate = useNavigate();
  const [error, setError] = useState(null);
  const [user, setUser] = useState(null);
  const [adverts, setAdverts] = useState([]);
  const [editOpen, setEditOpen] = useState(false);
  const [deleteUser, setDeleteUser] = useState(false);
  const [selectedAdvert, setSelectedAdvert] = useState(null);
  const [editingAdvert, setEditingAdvert] = useState(null);
  const [deleteAdvert, setDeleteAdvert] = useState(null);
  //const profileType = role === "ROLE_USER" ? "userProfile" : role === "ROLE_ADMIN" ? "userProfile" : "agencyProfile";
  const profileType = getProfileType();

  function openDetails(advert) {
    setSelectedAdvert(advert);
  }
  const handleUpdated = (updatedAdvert) => {
    setAdverts((prev) =>
      prev.map((a) => (a.id === updatedAdvert.id ? updatedAdvert : a)),
    );
  };
  useEffect(() => {
    async function fetchData() {
      const resProfil = await fetch(
        `http://localhost:8080/api/${profileType.toLowerCase()}Profile/${userID}`,
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        },
      );
      if (!resProfil) {
        setError("User not found");
        return;
      }
      const userData = await resProfil.json();
      console.log(userData);
      setUser(userData);

      const resAdvert = await fetch(
        "http://localhost:8080/api/advert/myAdverts",
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        },
      );

      if (!resAdvert.ok) {
        setError("Could not fetch adverts");
      }

      const advertData = await resAdvert.json();
      setAdverts(advertData);
    }

    fetchData();
  }, [userID]);
  function handleEditAdvert(advert) {
    setEditingAdvert(advert);
  }
  const getImageUrl = (filePath) => {
    if (!filePath) {
      return null;
    }
    return "http://localhost:8080/" + filePath.replace(/\\/g, "/");
  };

  if (error) return <div>{error}</div>;
  if (!user) return <div>Loading...</div>;
  return (
    <>
      <header className={styles.header}>
        <div className={styles.profile}>
          <img
            src={user.avatarUrl ? user.avatarUrl : "https://aurnchyhllskmomhcrxy.supabase.co/storage/v1/object/public/images/no-image-user.png"}
            alt="Profile Picture"
            className={styles.profileImg}
          ></img>
          <div className={styles.profileInfo}>
            <h3>{`${user.userName}`}</h3>
            <h4 style={{ margin: 0 }}>
              {profileType === "userProfile"
                ? `${user.name} ${user.surname}`
                : user.agencyName}
            </h4>
            <p className={styles.email}>{user.email}</p>
            <div className={styles.meta}>
              <span>
                <FiPhone />
                {user.phone}
              </span>
              <span>
                <FiMapPin />
                {user.city?.name}
              </span>
              <span>
                <FiCalendar /> Profile since {formatDate(user.createdAt)}
              </span>
            </div>
          </div>
        </div>
        <div className={styles.buttons}>
          <button onClick={() => setEditOpen(true)} className={styles.edit}>
            Edit profile
            <Edit2 size={16} />
          </button>
          {editOpen && (
            <EditProfileModal onClose={() => setEditOpen(false)} user={user} />
          )}
          <Form action="/logout" method="post">
            <button className={styles.logout}>
              Log out
              <LogOut size={16} />
            </button>
          </Form>
          <button className={styles.delete} onClick={() => setDeleteUser(true)}>
            Delete profile
          </button>
        </div>
      </header >
      <div>
        <h2 style={{ marginLeft: "50px", marginTop: "50px" }}>My adverts</h2>
        <Property
          adverts={adverts}
          handleDetails={openDetails}
          edit={true}
          editingAdvert={handleEditAdvert}
          onDeleteOpen={setDeleteAdvert}
        />
        {deleteUser && (
          <DeleteUserModal
            onClose={() => {
              setDeleteUser(false);
            }}
            id={user.id}
            profileType={user.profileType}
          />
        )}
        {selectedAdvert && (
          <AdvertDetailsModal
            advert={selectedAdvert}
            onClose={() => setSelectedAdvert(null)}
          />
        )}
        {editingAdvert && (
          <EditAdvertModal
            onClose={() => setEditingAdvert(null)}
            advert={editingAdvert}
            onUpdated={handleUpdated}
          />
        )}
        {deleteAdvert && (
          <DeleteAdvertModal
            onClose={() => setDeleteAdvert(null)}
            id={deleteAdvert}
          />
        )}
      </div>
    </>
  );
}
