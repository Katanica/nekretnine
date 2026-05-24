import styles from "./css/Property.module.css";
import NEKRETNINE from "../data/nekretnine.json";
import { Heart, MapPin, Maximize2, BedDouble, Bath, Car } from "lucide-react";

export default function Property() {
  return (
    <div className={styles.page}>
      <div className={styles.header}>
        <h2 className={styles.title}>Izdvojene nekretnine</h2>
        <ul className={styles.grid}>
          {NEKRETNINE.map((oglas) => (
            <li key={oglas.id} className={styles.card}>
              <div className={styles.imageWrap}>
                <img
                  src={oglas.slika}
                  alt={oglas.naslov}
                  className={styles.image}
                />
                <span
                  className={`${styles.badge} ${oglas.tip === "Najam" ? styles.badgeNajam : styles.badgeProdaja}`}
                >
                  {oglas.tip}
                </span>
                <button className={styles.heart} aria-label="Spremi oglas">
                  <Heart size={16} />
                </button>
              </div>
              <div className={styles.body}>
                <p className={styles.cijena}>
                  {oglas.cijena.toLocaleString("hr-HR")} {oglas.valuta}
                </p>
                <div className={styles.meta}>
                  <span>
                    <MapPin size={13} /> {oglas.lokacija.grad},{" "}
                    {oglas.lokacija.kvart}
                  </span>
                  <span>
                    <Maximize2 size={13} /> {oglas.kvadratura} m²
                  </span>
                </div>
                <div className={styles.detalji}>
                  <span>
                    <BedDouble size={15} /> {oglas.sobe}
                  </span>
                  <span>
                    <Bath size={15} /> {oglas.kupaonice}
                  </span>
                  <span>
                    <Car size={15} /> {oglas.parking}
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
