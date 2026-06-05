import { NavLink, useNavigate } from "react-router-dom";
import styles from "./css/NavBar.module.css";
import { Heart, User, Plus } from "lucide-react";
import { getToken, getUserID } from "../api";
export default function NavBar() {
  const token = getToken();

  const navigate = useNavigate();
  const id = getUserID();
  function handleProfil() {
    if (token !== "EXPIRED") {
      navigate(`profile/${id}`);
    } else {
      navigate("/login");
    }
  }

  function handleAddAdvert() {
    console.log(token);
    console.log(id);
    navigate("add-advert");
  }
  return (
    <nav className={styles.nav}>
      <div className={styles.inner}>
        <a href="/" className={styles.logo}>
          <svg
            viewBox="0 0 32 32"
            fill="none"
            xmlns="http://www.w3.org/2000/svg"
            width="32"
            height="32"
          >
            <path
              d="M4 14L16 4L28 14V27C28 27.55 27.55 28 27 28H20V21H12V28H5C4.45 28 4 27.55 4 27V14Z"
              fill="#c49a3c"
              fillOpacity="0.18"
              stroke="#c49a3c"
              strokeWidth="1.8"
              strokeLinejoin="round"
            />
            <path
              d="M16 4L28 14"
              stroke="#c49a3c"
              strokeWidth="1.8"
              strokeLinecap="round"
            />
            <path
              d="M4 14L16 4"
              stroke="#c49a3c"
              strokeWidth="1.8"
              strokeLinecap="round"
            />
          </svg>
          Nekretnine
        </a>
        <ul className={styles.links}>
          {[
            { to: "/", label: "Home" },

            { to: "/", label: "About us" },
            { to: "/", label: "Contact" },
          ].map(({ to, label }) => (
            <li key={label}>
              <NavLink
                to={to}
                end={to === "/"}
                className={({ isActive }) =>
                  isActive ? `${styles.link} ${styles.active}` : styles.link
                }
              >
                {label}
              </NavLink>
            </li>
          ))}
        </ul>
        <div className={styles.right}>
          <button className={styles.iconBtn} aria-label="Bookmarks">
            <Heart size={20} />
          </button>
          <button
            onClick={handleProfil}
            className={styles.iconBtn}
            aria-label="Profil"
          >
            <User size={20} />
          </button>
          {token && (
            <button className={styles.cta} onClick={handleAddAdvert}>
              <Plus size={16} />
              Add Advert
            </button>
          )}
        </div>
      </div>
    </nav>
  );
}
