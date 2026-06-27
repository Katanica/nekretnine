import { NavLink, useNavigate } from "react-router-dom";
import styles from "./css/NavBar.module.css";
import { Heart, User, Plus, Menu, X } from "lucide-react";
import { getToken, getUserID } from "../api";
import { useBookmarksContext } from "../context/BookmarksContext";
import { useState } from "react";

export default function NavBar() {
  const token = getToken();
  const navigate = useNavigate();
  const id = getUserID();
  const { bookmarkCount, sidebarOpen, openSidebar, closeSidebar } =
    useBookmarksContext();
  const [menuOpen, setMenuOpen] = useState(false);
  console.log("token", token);
  function handleProfil() {
    const t = getToken();
    setMenuOpen(false);
    if (t === null || t === "EXPIRED") {
      navigate("/login");
    } else {
      navigate(`profile/${id}`);
    }
  }

  function handleAddAdvert() {
    setMenuOpen(false);
    navigate("add-advert");
  }

  function handleBookmarks() {
    setMenuOpen(false);
    sidebarOpen ? closeSidebar() : openSidebar();
  }

  return (
    <nav className={styles.nav}>
      <div className={styles.inner}>
        {/* Logo */}
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

        {/* Desktop links */}
        <ul className={styles.links}>
          {[
            { to: "/Home", label: "Početna stranica" },
            { to: "/About-us", label: "O nama" },
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

        {/* Desktop actions */}
        <div className={styles.right}>
          {token !== "EXPIRED" && token !== null && (
            <button
              className={`${styles.iconBtn} ${styles.heartBtn}`}
              onClick={handleBookmarks}
              aria-label="Spremljeni oglasi"
            >
              <Heart
                size={20}
                fill={sidebarOpen ? "#c49a3c" : "none"}
                color={sidebarOpen ? "#c49a3c" : "currentColor"}
              />
              {bookmarkCount > 0 && (
                <span className={styles.badge}>
                  {bookmarkCount > 99 ? "99+" : bookmarkCount}
                </span>
              )}
            </button>
          )}
          <button
            onClick={handleProfil}
            className={styles.iconBtn}
            aria-label="Profil"
          >
            <User size={20} />
          </button>
          {token !== "EXPIRED" && token !== null && (
            <button className={styles.cta} onClick={handleAddAdvert}>
              <Plus size={16} />
              Dodaj oglas
            </button>
          )}
        </div>

        {/* Hamburger button — mobile only */}
        <button
          className={styles.hamburger}
          onClick={() => setMenuOpen((o) => !o)}
          aria-label="Prikaži menu"
        >
          {menuOpen ? <X size={22} /> : <Menu size={22} />}
        </button>
      </div>

      {/* Mobile menu */}
      {menuOpen && (
        <div className={styles.mobileMenu}>
          <ul className={styles.mobileLinks}>
            {[
              { to: "/Home", label: "Početna stranica" },
              { to: "/About-us", label: "O nama" },
            ].map(({ to, label }) => (
              <li key={label}>
                <NavLink
                  to={to}
                  end={to === "/"}
                  onClick={() => setMenuOpen(false)}
                  className={({ isActive }) =>
                    isActive
                      ? `${styles.mobileLink} ${styles.mobileLinkActive}`
                      : styles.mobileLink
                  }
                >
                  {label}
                </NavLink>
              </li>
            ))}
          </ul>

          <div className={styles.mobileDivider} />

          <div className={styles.mobileActions}>
            {token && (
              <button
                className={styles.mobileActionBtn}
                onClick={handleBookmarks}
              >
                <Heart
                  size={16}
                  fill={sidebarOpen ? "#c49a3c" : "none"}
                  color={sidebarOpen ? "#c49a3c" : "currentColor"}
                />
                Spremljeni oglasi
                {bookmarkCount > 0 && (
                  <span className={styles.mobileBadge}>{bookmarkCount}</span>
                )}
              </button>
            )}
            <button className={styles.mobileActionBtn} onClick={handleProfil}>
              <User size={16} />
              Profil
            </button>
            {token !== "EXPIRED" && token !== null && (
              <button
                className={`${styles.mobileActionBtn} ${styles.mobileCtaBtn}`}
                onClick={handleAddAdvert}
              >
                <Plus size={16} />
                Dodaj oglas
              </button>
            )}
          </div>
        </div>
      )}
    </nav>
  );
}
