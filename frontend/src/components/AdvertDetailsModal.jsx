import { createPortal } from "react-dom";
import styles from "./css/AdvertDetails.module.css";
import { useState, useEffect } from "react";
import { ChevronLeft, ChevronRight, MapPin } from "lucide-react";
import { getToken } from "../api";

export default function AdvertDetailsModal({ onClose, advert }) {
  let imageUrls = advert.imageUrls.length > 0 ? advert.imageUrls : ["https://aurnchyhllskmomhcrxy.supabase.co/storage/v1/object/public/images/images%20not%20uploaded.png"];
  const total = imageUrls?.length || 0;
  const [currentImg, setCurrentImg] = useState(total === 0 ? 0 : 1);

  const BASE_URL = "http://localhost:8080";

  const slide = (x) => {
    if (total != 0) {
      if (currentImg == 1 && x == -1)
        setCurrentImg(total);
      else if (currentImg == total && x == 1) {
        setCurrentImg(1);
      }
      else setCurrentImg(currentImg + x);
    }
  };
  const formatDate = (dateStr) => {
    return new Date(dateStr).toLocaleDateString("hr-HR");
  };
  const getImageUrl = () => {
    if (!filePath) return "/placeholder.jpg";
    return "http://localhost:8080/" + filePath.replace(/\\/g, "/");
  }
  const propertyTypeMap = { FLAT: "Stan", HOUSE: "Kuća", LAND: "Zemljište" };
  const advertTypeMap = { SALE: "Prodaja", RENT: "Najam" };

  return createPortal(
    <div className={styles.overlay} onClick={onClose}>
      <div className={styles.modal} onClick={(e) => e.stopPropagation()}>
        <div className={styles.sliderWrap}>
          <img
            src={imageUrls[currentImg - 1]}
            alt={advert.title}
            className={styles.sliderImg}
          />
          <button
            className={styles.sliderBtn}
            style={{ left: 12 }}
            onClick={() => slide(-1)}
          >
            <ChevronLeft size={18} />
          </button>
          <button
            className={styles.sliderBtn}
            style={{ right: 12 }}
            onClick={() => slide(1)}
          >
            <ChevronRight size={18} />
          </button>
          <span className={styles.counter}>
            {total === 0 ? `0 / 0` : `${currentImg} / ${total}`}
          </span>
          <span className={styles.badge}>
            {advertTypeMap[advert.advertType]}
          </span>
        </div>
        <div className={styles.content}>
          <div className={styles.titleRow}>
            <div>
              <h2>{advert.title}</h2>
              <p>
                <MapPin size={13} /> {advert.city?.name}
              </p>
            </div>
            <div>
              <p className={styles.price}>
                {advert.price.toLocaleString("hr-HR")} KM
              </p>
            </div>
          </div>

          <div className={styles.stats}>
            <div>
              <span>Površina</span>
              <strong>{advert.size} m²</strong>
            </div>
            <div>
              <span>Tip</span>
              <strong>{propertyTypeMap[advert.propertyType]}</strong>
            </div>
            <div>
              <span>Objavljeno</span>
              <strong>{formatDate(advert.postedAt)}</strong>
            </div>
          </div>

          {advert.description?.length === 0 ? (
            <p className={styles.description}>Nema opisa</p>
          ) : (
            <p className={styles.description}>
              Opis:
              <br />
              {advert.description}
            </p>
          )}
          <div className={styles.actions}>
            <button>Spremi</button>
            <button>Kontaktiraj</button>
          </div>
        </div>

        <button className={styles.closeBtn} onClick={onClose}>
          ✕
        </button>
      </div>
    </div>,
    document.body
  );
}
