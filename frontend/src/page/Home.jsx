import styles from "./css/Home.module.css";
import Property from "../components/Property";
import CategoryFilter from "../components/CategoryFilter";
import Header from "../components/Header";
import PageBar from "../components/PageBar";
import { useEffect, useState } from "react";
import { PAGE_SIZE } from "../constants";
import AdvertDetailsModal from "../components/AdvertDetailsModal";

import RegisterForm from "../components/RegisterForm";
import { redirect } from "react-router-dom";
import { MdOutlineAirlineSeatIndividualSuite } from "react-icons/md";

export default function HomePage() {
  const [adverts, setAdverts] = useState([]);
  const [error, setError] = useState(null);
  const [selectedAdvert, setSelectedAdvert] = useState(null);
  const [advertNumber, setAdvertNumber] = useState(0);
  const [page, setPage] = useState(0);
  const [pageSize, setPageSize] = useState(PAGE_SIZE);

  function openDetails(advert) {
    setSelectedAdvert(advert);
  }

  useEffect(() => {
    async function fetchingData() {
      const resAdverts = await fetch(`http://localhost:8080/api/advert?page=${page}&size=${pageSize}`);

      const countData = await fetch("http://localhost:8080/api/advert/countAdverts");
      const count = await countData.json();
      setAdvertNumber(count);

      if (!resAdverts.ok) {
        setError("Could not fetch data...");
      }

      const advertsData = await resAdverts.json();

      setAdverts(advertsData.content);

      console.log("HOME: ", advertsData.content);
    }
    fetchingData();
  }, [page]);

  if (error) return <div>{error}</div>;
  return (
    <div className={styles.content}>
      <Header />

      <div className={styles.listings}>
        <Property
          adverts={adverts}
          handleDetails={openDetails}
          title="Top picks"
        />
        {selectedAdvert && (
          <AdvertDetailsModal
            advert={selectedAdvert}
            onClose={() => setSelectedAdvert(null)}
          />
        )}
      </div>
      {adverts.length > 0 && <PageBar adverts={adverts} advertNumber={advertNumber} page={page} pageSize={pageSize} onPageChange={setPage} />}
    </div>
  );
}
