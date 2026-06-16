import { createPortal } from "react-dom";
import styles from "./css/AdvertDetails.module.css";
import { useState, useEffect } from "react";
import { ChevronLeft, ChevronRight, MapPin } from "lucide-react";
import { getToken } from "../api";

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
  };

  const formatDate = (dateStr) =>
    new Date(dateStr).toLocaleDateString("hr-HR");

  if (loading) {
    return createPortal(
      <div className={styles.overlay} onClick={onClose}>
        <div className={styles.modal} onClick={(e) => e.stopPropagation()}>
          <div className={styles.loadingWrap}>
            <div className={styles.spinner} />
          </div>
          <button className={styles.closeBtn} onClick={onClose}>
            ✕
          </button>
        </div>
      </div>,
      document.body
    );
  }

  if (!advertData) return null;

  return createPortal(
    <div className={styles.overlay} onClick={onClose}>
      <div className={styles.modal} onClick={(e) => e.stopPropagation()}>
        <div className={styles.sliderWrap}>
          <img
            src={advert.imageUrls[currentImg - 1]}
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
            {advertTypeMap[advertData.advertType]}
          </span>
        </div>
        <div className={styles.content}>
          <div className={styles.titleRow}>
            <div>
              <h2>{advertData.title}</h2>
              <p>
                <MapPin size={13} /> {advertData.city?.name}
              </p>
            </div>
            <div>
              <p className={styles.price}>
                {advertData.price.toLocaleString("hr-HR")} KM
              </p>
            </div>
          </div>

          <div className={styles.stats}>
            <div>
              <span>Površina</span>
              <strong>{advertData.size} m²</strong>
            </div>
            <div>
              <span>Tip</span>
              <strong>{propertyTypeMap[advertData.propertyType]}</strong>
            </div>
            <div>
              <span>Objavljeno</span>
              <strong>{formatDate(advertData.postedAt)}</strong>
            </div>
          </div>

          {advertData.description?.length === 0 ? (
            <p className={styles.description}>Nema opisa</p>
          ) : (
            <p className={styles.description}>
              Opis:
              <br />
              {advertData.description}
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
