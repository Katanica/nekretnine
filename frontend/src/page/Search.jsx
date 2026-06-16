import { useSearchParams } from "react-router-dom";
import { useState, useRef, useEffect } from "react";
import styles from "./css/Home.module.css";
import Property from "../components/Property";
import HeaderSearch from "../components/HeaderSearch";
import Header from "../components/Header";
import AdvertDetailsModal from "../components/AdvertDetailsModal";

export default function Search() {
    const [searchParams] = useSearchParams();
    const [adverts, setAdverts] = useState([]);
    const [selectedAdvert, setSelectedAdvert] = useState(null);

    const minPrice = searchParams.get("minPrice");
    const maxPrice = searchParams.get("maxPrice");
    const minSize = searchParams.get("minSize");
    const maxSize = searchParams.get("maxSize");
    const propertyType = searchParams.get("propertyType");
    const cityId = searchParams.get("cityId");
    const cantonId = searchParams.get("cantonId");
    const title = searchParams.get("title");
    const advertType = searchParams.get("advertType");

    const params = [{ "minPrice": minPrice },
    { "maxPrice": maxPrice }, { "minSize": minSize },
    { "maxSize": maxSize }, { "propertyType": propertyType }, { "cityId": cityId }, { "cantonId": cantonId }, { "title": title }, { "advertType": advertType }];

    let request = params
        .filter(p => Object.values(p)[0] !== null)
        .map(p => {
            const [key, value] = Object.entries(p)[0];
            return `${key}=${value}`;
        })
        .join("&");

    function openDetails(advert) {
        setSelectedAdvert(advert);
    }

    useEffect(() => {
        async function fetchingData() {
            const resAdverts = await fetch(`http://localhost:8080/api/advert/find?${request}`, {
                method: "GET"
            });

            if (!resAdverts.ok) {
                setError("Could not fetch data...");
            }

            const advertsData = await resAdverts.json();

            setAdverts(advertsData);
        }
        fetchingData();
    }, [request]);



    return (
        <div className={styles.content}>
            <HeaderSearch />
            <div className={styles.listings} style={{ marginTop: "100px" }}>
                <Property adverts={adverts} handleDetails={openDetails} title="Nekretnine po filterima" />
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