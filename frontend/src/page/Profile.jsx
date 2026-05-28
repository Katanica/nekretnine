import styles from "./css/Profile.module.css";
import konj from "../assets/konj.jpg";
import { FiPhone, FiMapPin, FiCalendar } from "react-icons/fi";
import { Form, useParams } from "react-router-dom";
import { useEffect, useState } from "react";
import { getToken } from "../api";
import Property from "../components/Property";
export default function Profile() {
  const { id } = useParams();
  const token = getToken();
  const [error, setError] = useState(null);
  const [user, setUser] = useState(null);
  const [adverts, setAdverts] = useState([]);
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
      setUser(userData);

      const resAdvert = await fetch("http://localhost:8080/api/advert");

      if (!resAdvert.ok) {
        setError("Could not fetch adverts");
      }

      const advertData = await resAdvert.json();
      setAdverts(advertData);
    }

    fetchData();
  }, [id]);

  if (error) return <div>{error}</div>;
  if (!user) return <div>Loading...</div>;
  return (
    <>
      <header className={styles.header}>
        <div className={styles.profile}>
          <img
            src={konj}
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
          <button className={styles.edit}>Edit profile</button>
          <Form action="/logout" method="post">
            <button className={styles.logout}>Log out</button>
          </Form>
        </div>
      </header>
      <div>
        <h2>My adverts</h2>
        <Property />
      </div>
    </>
  );
}
