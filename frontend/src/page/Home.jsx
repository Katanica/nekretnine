import styles from "./css/Home.module.css";
import Property from "../components/Property";
import CategoryFilter from "../components/CategoryFilter";
import Header from "../components/Header";
import { useEffect, useState } from "react";
import AdvertDetailsModal from "../components/AdvertDetailsModal";

import RegisterForm from "../components/RegisterForm";
import { redirect } from "react-router-dom";


export default function HomePage() {
  const [adverts, setAdverts] = useState([]);
  const [error, setError] = useState(null);
  const [selectedAdvert, setSelectedAdvert] = useState(null);

  function openDetails(advert) {
    setSelectedAdvert(advert);
  }

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

      <div className={styles.listings}>
        <Property adverts={adverts} handleDetails={openDetails} title="Izdvojene nekretnine" />
        {selectedAdvert && (
          <AdvertDetailsModal
            advert={selectedAdvert}
            onClose={() => setSelectedAdvert(null)}
          />
        )}
      </div>
    </div>
  );
}
