import styles from "./css/Header.module.css";

export default function Header() {
  return (
    <div className={styles.hero}>
      <div className={styles.heroImage}>
        <div className={styles.overlay}>
          <div className={styles.heroText}>
            <h1>
              Pronađite savršenu
              <br />
              nekretninu za sebe
            </h1>
            <p>Pretražite tisuće provjerenih nekretnina na jednom mjestu</p>
            <div className={styles.heroButtons}>
              <button className={styles.btnPrimary}>🔍 Pretraži ponudu</button>
              <button className={styles.btnSecondary}>
                👤 Prodaj nekretninu
              </button>
            </div>
          </div>
        </div>
        <div className={styles.searchBar}>
          <div className={styles.searchField}>
            <label>Location</label>
            <input type="text" placeholder="Enter city or street" />
          </div>
          <div className={styles.searchField}>
            <label>Type</label>
            <select>
              <option>Flat</option>
              <option>House</option>
              <option>Land</option>
              <option>Business place</option>
            </select>
          </div>
          <div className={styles.searchField}>
            <label>Price</label>
            <select>
              <option>0 € – 500.000 €</option>
              <option>0 € – 100.000 €</option>
              <option>100.000 € – 300.000 €</option>
            </select>
          </div>
          <div className={styles.searchField}>
            <label>Square</label>
            <select>
              <option>m²</option>
              <option>do 50 m²</option>
              <option>50 – 100 m²</option>
              <option>100+ m²</option>
            </select>
          </div>
          <button className={styles.searchBtn}>🔍 Search</button>
        </div>
      </div>
    </div>
  );
}
