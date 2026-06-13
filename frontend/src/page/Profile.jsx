import styles from "./css/Profile.module.css";
import konj from "../assets/konj.jpg";
import { FiPhone, FiMapPin, FiCalendar } from "react-icons/fi";
import { Form, useParams, useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import { getToken, getUserID, getRole } from "../api";
import Property from "../components/Property";
import { formatDate } from "../formatDate";
import AdvertDetailsModal from "../components/AdvertDetailsModal";

import EditProfileModal from "../components/EditProfileModal";
export default function Profile() {
  const userID = getUserID();
  const role = getRole();
  const token = getToken();
  const navigate = useNavigate();
  const [error, setError] = useState(null);
  const [user, setUser] = useState(null);
  const [adverts, setAdverts] = useState([]);
  const [editOpen, setEditOpen] = useState(false);
  const [selectedAdvert, setSelectedAdvert] = useState(null);

  const profileType = role === "ROLE_USER" ? "userProfile" : "agencyProfile";
  console.log(profileType);
  // za otvaranje info o advertu
  function openDetails(advert) {
    setSelectedAdvert(advert);
  }

  useEffect(() => {
    async function fetchData() {
      const resProfil = await fetch(
        `http://localhost:8080/api/${profileType}/${userID}`,
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
            src={user.avatarUrl}
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
                {user.cityName}
              </span>
              <span>
                <FiCalendar /> Profile since {formatDate(user.createdAt)}
              </span>
            </div>
          </div>
        </div>
        <div className={styles.buttons}>
          <button className={styles.add}>Add Advert</button>
          <button onClick={() => setEditOpen(true)} className={styles.edit}>
            Edit profile
          </button>
          {editOpen && (
            <EditProfileModal onClose={() => setEditOpen(false)} user={user} />
          )}
          <Form action="/logout" method="post">
            <button className={styles.logout}>Log out</button>
          </Form>
        </div>
      </header>
      <div>
        <h2>My adverts</h2>
        <Property adverts={adverts} handleDetails={openDetails} />
        {selectedAdvert && (
          <AdvertDetailsModal
            advert={selectedAdvert}
            onClose={() => setSelectedAdvert(null)}
          />
        )}
      </div>
    </>
  );
}
