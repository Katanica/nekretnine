import { useSearchParams } from "react-router-dom";
import { useState, useRef, useEffect } from "react";
import styles from "./css/Home.module.css";
import Property from "../components/Property";
import HeaderSearch from "../components/HeaderSearch";
import PageBar from "../components/PageBar";
import { PAGE_SIZE } from "../constants";
import Header from "../components/Header";
import AdvertDetailsModal from "../components/AdvertDetailsModal";

export default function Search() {
    const [searchParams] = useSearchParams();
    const [adverts, setAdverts] = useState([]);
    const [selectedAdvert, setSelectedAdvert] = useState(null);
    const [advertNumber, setAdvertNumber] = useState(0);
    const [page, setPage] = useState(0);
    const [pageSize, setPageSize] = useState(PAGE_SIZE);

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

    console.log("SEARCH, request: ", request);

    function openDetails(advert) {
        setSelectedAdvert(advert);
    }

    useEffect(() => {
        async function fetchingData() {

            const resAdverts = await fetch(`http://localhost:8080/api/advert/find?${request}&page=${page}&size=${pageSize}`, {
                method: "GET"
            });
            console.log("SEARCH, resadverts: ", resAdverts);
            const countData = await fetch("http://localhost:8080/api/advert/countAdverts");
            const count = await countData.json();
            setAdvertNumber(count);

            if (!resAdverts.ok) {
                setError("Could not fetch data...");
            }

            const advertsData = await resAdverts.json();
            console.log("SEARCH: ", advertsData);

            setAdverts(advertsData);
        }
        fetchingData();
    }, [request, page]);



    return (
        <div className={styles.content}>
            <HeaderSearch style={{ position: "relative" }} />

            <div className={styles.listings} style={{ marginTop: "100px" }}>

                <Property adverts={adverts} handleDetails={openDetails} title="Nekretnine po filterima" />
                {selectedAdvert && (
                    <AdvertDetailsModal
                        advert={selectedAdvert}
                        onClose={() => setSelectedAdvert(null)}
                    />
                )}


            </div>

            {adverts.length > 0 && (<PageBar adverts={adverts} advertNumber={advertNumber} page={page} pageSize={pageSize} onPageChange={setPage} />)}


        </div >
    );
}