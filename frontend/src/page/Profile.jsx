import styles from "./css/Profile.module.css";
import konj from "../assets/konj.jpg";
import { FiPhone, FiMapPin, FiCalendar } from "react-icons/fi";
import { Form } from "react-router-dom";
export default function Profile() {
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
            <h3>Konj</h3>
            <p className={styles.email}> email</p>
            <div className={styles.meta}>
              <span>
                <FiPhone /> 0647855
              </span>
              <span>
                <FiMapPin /> Jablanica
              </span>
              <span>
                <FiCalendar /> Profile since 1808
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
      </div>
    </>
  );
}
