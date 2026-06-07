import styles from "./css/Property.module.css";
import podrum from "../assets/podrum.jpg";
import { Heart, MapPin, Maximize2, BedDouble, Bath, Car } from "lucide-react";
/*
advertType: "SALE"
cityId: null
cityName: null
description: "yfaffafaaf"
id: 1
postedAt: "2026-05-28T22:06:27.397998"
price: 1212
propertyType: null
size: 131313
title: "aadadadadad"
updatedAt: "2026-05-28T22:06:27.397998"
*/
export default function Property({ adverts }) {
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
        <h2 className={styles.title}>Izdvojene nekretnine</h2>
        <ul className={styles.grid}>
          {adverts.map((advert) => (
            <li key={advert.id} className={styles.card}>
              <div className={styles.imageWrap}>
                <img
                  src={getImageUrl(advert.pictures[0]?.filePath)}
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
                <p className={styles.cijena}>
                  {advert.price.toLocaleString("hr-HR")} {advert.valuta}
                </p>
                <div className={styles.meta}>
                  <span>
                    <MapPin size={13} /> {advert.cityName}, {advert.cityName}
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
