import styles from "./css/Property.module.css";
import podrum from "../assets/podrum.jpg";
import { Heart, MapPin, Maximize2, BedDouble, Bath, Car } from "lucide-react";

export default function Property({ adverts, handleDetails, title }) {
  const getImageUrl = (filePath) => {
    if (!filePath) {
      console.log("nema slike");
      return null;
    }
    return "http://localhost:8080/" + filePath.replace(/\\/g, "/");
  };
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
                  src={advert.imageUrls[0]}
                  alt={advert.title}
                  className={styles.image}
                />
                <span
                  className={`${styles.badge} ${advert.advertType === "RENT" ? styles.badgeNajam : styles.badgeProdaja}`}
                >
                  {advert.advertType}
                </span>

                <button className={styles.heart} aria-label="Spremi advert">
                  <Heart size={16} />
                </button>
              </div>
              <div className={styles.body}>
                <h3 style={{ margin: "0" }}>
                  {advert.title}
                </h3>
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
                <div className={styles.description}>
                  <span>
                    <BedDouble size={15} /> 3
                  </span>
                  <span>
                    <Bath size={15} /> 2
                  </span>
                  <span>
                    <Car size={15} /> 1
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
