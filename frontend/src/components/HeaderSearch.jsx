import styles from "./css/Header.module.css";
import { useState, useRef, useEffect } from "react";
import { Form, useNavigate, useSearchParams } from "react-router-dom";

export default function Header() {
  const [cantons, setCantons] = useState([]);
  const [canton, setCanton] = useState("");
  const [cities, setCities] = useState([]);
  const [city, setCity] = useState("");
  const [minPrice, setMinPrice] = useState("");
  const [maxPrice, setMaxPrice] = useState("");
  const [minSize, setMinSize] = useState("");
  const [maxSize, setMaxSize] = useState("");
  const [propertyType, setPropertyType] = useState("");
  const [advertType, setAdvertType] = useState("");
  const navigate = useNavigate();
  const [searchParams] = useSearchParams();
  const [title, setTitle] = useState("");

  const handleCantonChange = (event) => {
    const value = event.target.value;
    setCity("");
    setCanton(value);
    if (value) loadCities(value);
  }

  const handleCityChange = (event) => {
    const value = event.target.value;
    setCity(value);
  }

  const loadCities = (id) => {
    async function fetchingData() {
      const resCities = await fetch(`http://localhost:8080/api/city/byCanton/${id}`);

      if (!resCities.ok) {
        setError("Could not fetch data...");
      }

      const citiesData = await resCities.json();
      setCities(citiesData);
    }
    fetchingData();
  }

  useEffect(() => {
    async function fetchingData() {
      const resCantons = await fetch("http://localhost:8080/api/canton");
      const cantonsData = await resCantons.json();
      setCantons(cantonsData);
    }
    fetchingData();
  }, []);

  useEffect(() => {
    if (canton)
      loadCities(canton);
  }, [canton]);

  useEffect(() => {
    // ZA SELECT I INPUT
    setMinPrice(searchParams.get("minPrice") || "");
    setMaxPrice(searchParams.get("maxPrice") || "");
    setMinSize(searchParams.get("minSize") || "");
    setMaxSize(searchParams.get("maxSize") || "");
    setPropertyType(searchParams.get("propertyType") || "");
    setCity(searchParams.get("cityId") || "");
    setCanton(searchParams.get("cantonId") || "");
    setTitle(searchParams.get("title") || "");
    setAdvertType(searchParams.get("advertType") || "");

  }, [searchParams]);

  const handleSubmit = (e) => {
    e.preventDefault();

    const params = new URLSearchParams();

    if (minPrice) params.append("minPrice", minPrice);
    if (maxPrice) params.append("maxPrice", maxPrice);
    if (minSize) params.append("minSize", minSize);
    if (maxSize) params.append("maxSize", maxSize);
    if (propertyType) params.append("propertyType", propertyType);
    if (city) params.append("cityId", city);
    if (canton) params.append("cantonId", canton);
    if (title) params.append("title", title);
    if (advertType) params.append("advertType", advertType);

    navigate(`/search?${params}`);
  }


  return (
    <div className={styles.hero}>
      <Form
        onSubmit={handleSubmit}
        method="post"
        encType="multipart/form-data">
        <div className={styles.searchBar} style={{ paddingTop: "15px", paddingBottom: "15px", bottom: "-130px" }}>
          <div style={{ display: "flex", flexDirection: "row", alignItems: "flex-end", gap: "16px", flexWrap: "wrap", width: "100%" }}>
            <label className={styles.searchField} style={{ flexGrow: "100" }}><label>Title</label><input onChange={e => setTitle(e.target.value)} value={title} placeholder="..." type="text"></input></label>
            <button className={styles.searchBtn} style={{ marginLeft: "auto" }} type="submit">🔍 Search</button>

          </div>
          <div style={{ display: "flex", flexDirection: "row", alignItems: "flex-end", gap: "16px", flexWrap: "wrap" }}>
            <label className={styles.searchField} htmlFor="advertType"><label>Advert type</label>
              <select value={advertType} onChange={e => setAdvertType(e.target.value)}>
                <option className={styles.searchField} value="">All advert types</option>
                <option value="SALE">Sale</option>
                <option value="RENTING">Renting</option>
              </select>
            </label>
            <label className={styles.searchField} htmlFor="propertyType"><label>Property type</label>
              <select value={propertyType} onChange={e => setPropertyType(e.target.value)}>
                <option className={styles.searchField} value="">All property types</option>
                <option value="FLAT">Flat</option>
                <option value="HOUSE">House</option>
                <option value="LAND">Land</option>
                <option value="BUSINESS PLACE">Business place</option>
              </select>
            </label>
            <label htmlFor="canton" className={styles.searchField}>
              <select
                name="canton"
                placeholder="Canton"
                onChange={handleCantonChange}
                value={canton}>
                <option className={styles.searchField} value="">All cantons</option>‚
                {
                  cantons?.map((canton) =>
                  (<option style={{ color: 'black' }} value={canton.id} key={canton.id}>
                    {canton.name}
                  </option>))

                }
              </select>
            </label>
            {canton && (
              <label htmlFor="cityId" className={styles.searchField}>
                <select
                  name="cityId"
                  placeholder="City"
                  onChange={handleCityChange}
                  value={city}>
                  <option className={styles.searchField} value="">All cities</option>
                  {
                    Object.values(cities).map((city) =>
                    (<option style={{ color: 'black' }} value={city.id} key={city.id}>
                      {city.name}
                    </option>))

                  }
                </select>
              </label>
            )}
            <label htmlFor="minPrice" className={styles.searchField}><label>Min price</label>
              <input type="number" onChange={e => setMinPrice(e.target.value)} value={minPrice} placeholder="KM"></input>
            </label>
            <label htmlFor="maxPrice" className={styles.searchField}><label>Max price</label>
              <input type="number" onChange={e => setMaxPrice(e.target.value)} value={maxPrice} placeholder="KM"></input>
            </label>
            <label htmlFor="minSize" className={styles.searchField}><label>Min size</label>
              <input type="number" onChange={e => setMinSize(e.target.value)} value={minSize} placeholder="m2"></input>
            </label>
            <label htmlFor="maxSize" className={styles.searchField}><label>Max size</label>
              <input type="number" onChange={e => setMaxSize(e.target.value)} value={maxSize} placeholder="m2"></input>
            </label>

          </div>
        </div>
      </Form>
    </div >
  );
}
