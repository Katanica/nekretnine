import styles from "./css/Profile.module.css";
import konj from "../assets/konj.jpg";
import { FiPhone, FiMapPin, FiCalendar } from "react-icons/fi";
import { Form, useParams, useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import { getToken } from "../api";
import Property from "../components/Property";
import EditProfile from "./EditProfile";
import EditProfileModal from "../components/EditProfileModal";
export default function Profile() {
  const { id } = useParams();
  const token = getToken();
  const navigate = useNavigate();
  const [error, setError] = useState(null);
  const [user, setUser] = useState(null);
  const [adverts, setAdverts] = useState([]);
  const [editOpen, setEditOpen] = useState(false);

  useEffect(() => {
    async function fetchData() {
      const resProfil = await fetch(
        `http://localhost:8080/api/userProfile/${id}`,
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        },
      );
      if (!resProfil.ok) {
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
  }, [id]);

  const getImageUrl = (filePath) => {
    if (!filePath) {
      console.log("nema slike");
      return null;
    }
    return "http://localhost:8080/" + filePath.replace(/\\/g, "/");
  };

  console.log(user);
  if (error) return <div>{error}</div>;
  if (!user) return <div>Loading...</div>;
  return (
    <>
      <header className={styles.header}>
        <div className={styles.profile}>
          <img
            src={getImageUrl(user.avatar?.filePath)}
            alt="Profile Picture"
            className={styles.profileImg}
          ></img>
          <div className={styles.profileInfo}>
            <h3>{`${user.name} ${user.surname}`}</h3>
            <p className={styles.email}>{user.email}</p>
            <div className={styles.meta}>
              <span>
                <FiPhone />
                {user.phone}
              </span>
              <span>
                <FiMapPin />
                Jablanica
              </span>
              <span>
                <FiCalendar /> Profile since {user.createdAt}
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
            <EditProfileModal
              onClose={() => setEditOpen(false)}
              email={user.email}
              userName={user.userName}
              name={user.name}
              surname={user.surname}
            />
          )}
          <Form action="/logout" method="post">
            <button className={styles.logout}>Log out</button>
          </Form>
        </div>
      </header>
      <div>
        <h2>My adverts</h2>
        <Property adverts={adverts} />
      </div>
    </>
  );
}
