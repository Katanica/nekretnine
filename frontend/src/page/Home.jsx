import NEKRETNINE from "../data/nekretnine.json";

import styles from "./css/Home.module.css";
import Property from "../components/Property";
import CategoryFilter from "../components/CategoryFilter";

export default function HomePage() {
  return (
    <div className={styles.content}>
      <CategoryFilter />

      <div className={styles.listings}>
        <Property />
      </div>
    </div>
  );
}
