import { X, Heart, MapPin } from "lucide-react";
import { createPortal } from "react-dom";
import styles from "./css/Bookmarks.module.css";
import { useBookmarksContext } from "../context/BookmarksContext";

export default function BookmarksSidebar() {
  const {
    bookmarks,
    removeBookmark,
    closeSidebar,
    sidebarOpen,
    openAdvertModal,
  } = useBookmarksContext();

  return createPortal(
    <>
      {sidebarOpen && <div className={styles.overlay} onClick={closeSidebar} />}
      <aside
        className={`${styles.sidebar} ${sidebarOpen ? styles.sidebarOpen : ""}`}
        aria-label="Bookmarks sidebar"
      >
        <div className={styles.header}>
          <div className={styles.headerLeft}>
            <Heart size={18} fill="#c49a3c" color="#c49a3c" />
            <h2 className={styles.title}>Bookmarks</h2>
          </div>
          <button
            onClick={closeSidebar}
            className={styles.closeBtn}
            aria-label="Close"
          >
            <X size={20} />
          </button>
        </div>

        <div className={styles.content}>
          {bookmarks.length === 0 ? (
            <div className={styles.empty}>
              <Heart size={40} />
              <p>No bookmarks yet</p>
              <span>Save adverts you like to find them later</span>
            </div>
          ) : (
            <ul className={styles.list}>
              {bookmarks.map((bookmark) => {
                const advert = bookmark.advert ?? bookmark;
                const advertId = bookmark.advertId ?? advert.id;

                return (
                  <li
                    key={advertId}
                    className={styles.item}
                    onClick={() => openAdvertModal(advert)}
                  >
                    <div className={styles.thumbnail}>
                      {advert.imageUrls?.[0] ? (
                        <img
                          src={advert.imageUrls[0]}
                          alt={advert.title}
                          className={styles.thumbImg}
                        />
                      ) : (
                        <div className={styles.thumbPlaceholder} />
                      )}
                    </div>
                    <div className={styles.info}>
                      <h4 className={styles.itemTitle}>{advert.title}</h4>
                      {advert.city?.name && (
                        <span className={styles.location}>
                          <MapPin size={12} />
                          {advert.city.name}
                        </span>
                      )}
                      <span className={styles.price}>
                        {advert.price?.toLocaleString("hr-HR")} KM
                      </span>
                    </div>
                    <button
                      className={styles.removeBtn}
                      onClick={(e) => {
                        e.stopPropagation();
                        removeBookmark(advertId);
                      }}
                      aria-label="Remove bookmark"
                    >
                      <X size={14} />
                    </button>
                  </li>
                );
              })}
            </ul>
          )}
        </div>
      </aside>
    </>,
    document.body,
  );
}
