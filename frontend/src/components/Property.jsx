import styles from "./css/Property.module.css";
import {
  Heart,
  MapPin,
  Maximize2,
  FileEditIcon,
  Trash2,
} from "lucide-react";
import { useBookmarksContext } from "../context/BookmarksContext";

export default function Property({
  adverts,
  handleDetails,
  edit = false,
  title,
  onEdit,
  editingAdvert,
  onDeleteOpen,
}) {
  const { isBookmarked, addBookmark, removeBookmark } = useBookmarksContext();

  function handleEdit(e, advert) {
    e.stopPropagation();
    editingAdvert(advert);
  }

  function handleDelete(e, id) {
    e.stopPropagation();
    onDeleteOpen(id);
  }

  function handleBookmarkToggle(e, advertId) {
    e.stopPropagation();
    if (isBookmarked(advertId)) {
      removeBookmark(advertId);
    } else {
      addBookmark(advertId);
    }
  }

  return (
    <div className={styles.page}>
      <div className={styles.header}>
        <h2 className={styles.title}>{title}</h2>
        <ul className={styles.grid}>
          {adverts?.map((advert) => (
            <li
              key={advert.id}
              className={styles.card}
              onClick={() => handleDetails(advert)}
            >
              <div className={styles.imageWrap}>
                <img
                  src={advert.imageUrls.length > 0 ? advert.imageUrls[0] : "https://aurnchyhllskmomhcrxy.supabase.co/storage/v1/object/public/images/images%20not%20uploaded.png"}
                  alt={advert.title}
                  className={styles.image}
                />

                <span
                  className={`${styles.badge} ${advert.advertType === "RENT" ? styles.badgeNajam : styles.badgeProdaja}`}
                >
                  {advert.advertType}
                </span>

                {edit === false ? (
                  <button
                    className={`${styles.heart} ${isBookmarked(advert.id) ? styles.heartActive : ""}`}
                    aria-label="Spremi advert"
                    onClick={(e) => handleBookmarkToggle(e, advert.id)}
                  >
                    <Heart
                      size={16}
                      fill={isBookmarked(advert.id) ? "#c49a3c" : "none"}
                      color={isBookmarked(advert.id) ? "#c49a3c" : "currentColor"}
                    />
                  </button>
                ) : (
                  <>
                    <button
                      onClick={(e) => handleEdit(e, advert)}
                      className={styles.edit}
                      aria-label="Uredi advert"
                    >
                      <FileEditIcon size={16} />
                    </button>
                    <button
                      onClick={(e) => handleDelete(e, advert.id)}
                      className={styles.deleteBtn}
                    >
                      <Trash2 size={16} />
                    </button>
                  </>
                )}
              </div>
              <div className={styles.body}>
                <h3 style={{ margin: "0" }}>{advert.title}</h3>
                <p className={styles.cijena}>
                  {advert.price.toLocaleString("hr-HR")} KM
                </p>
                <div className={styles.meta}>
                  <span>
                    <MapPin size={13} /> {advert.city?.name}
                  </span>
                  <span>
                    <Maximize2 size={13} /> {advert.size} m²
                  </span>
                </div>
              </div>
            </li>
          ))}
        </ul>
      </div>
    </div>
  );
}
