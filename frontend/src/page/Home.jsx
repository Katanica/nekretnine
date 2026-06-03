import styles from "./css/Home.module.css";
import Property from "../components/Property";
import CategoryFilter from "../components/CategoryFilter";
import Header from "../components/Header";
import { useEffect, useState } from "react";

export default function HomePage() {
  const [adverts, setAdverts] = useState([]);
  const [error, setError] = useState(null);
  useEffect(() => {
    async function fetchingData() {
      const resAdverts = await fetch("http://localhost:8080/api/advert");

      if (!resAdverts.ok) {
        setError("Could not fetch data...");
      }

      const advertsData = await resAdverts.json();

      setAdverts(advertsData.content);
    }
    fetchingData();
  }, []);

  if (error) return <div>{error}</div>;
  return (
    <div className={styles.content}>
      <Header />

      <CategoryFilter />

      <div className={styles.listings}>
        <Property adverts={adverts} />
      </div>
    </div>
  );
}
