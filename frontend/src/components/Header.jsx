import styles from "./css/Header.module.css";
import { useState, useRef, useEffect } from "react";
import { Form, useNavigate } from "react-router-dom";
import { getToken } from "../api";
export default function Header() {
  const [cantons, setCantons] = useState([]);
  const [canton, setCanton] = useState(null);
  const [cities, setCities] = useState([]);
  const [city, setCity] = useState(null);
  const [minPrice, setMinPrice] = useState(null);
  const [maxPrice, setMaxPrice] = useState(null);
  const [minSize, setMinSize] = useState(null);
  const [maxSize, setMaxSize] = useState(null);
  const [propertyType, setPropertyType] = useState(null);
  const [advertType, setAdvertType] = useState(null);
  const [title, setTitle] = useState(null);
  const token = getToken();
  const navigate = useNavigate();

  const handleCantonChange = (event) => {
    const value = event.target.value;
    setCanton(value);
    if (value) loadCities(value);
  };

  const handleCityChange = (event) => {
    const value = event.target.value;
    setCity(value);
  };

  const loadCities = (id) => {
    async function fetchingData() {
      const resCities = await fetch(
        `http://localhost:8080/api/city/byCanton/${id}`,
      );

      if (!resCities.ok) {
        setError("Could not fetch data...");
      }

      const citiesData = await resCities.json();
      setCities(citiesData);
    }
    fetchingData();
  };

  useEffect(() => {
    async function fetchingData() {
      const resCantons = await fetch("http://localhost:8080/api/canton");

      if (!resCantons.ok) {
        setError("Could not fetch data...");
      }

      const cantonsData = await resCantons.json();
      setCantons(cantonsData);
    }
    fetchingData();
  }, []);

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
  };

  const handleFilterClear = () => {
    setMinPrice("");
    setMaxPrice("");
    setMinSize("");
    setMaxSize("");
    setPropertyType("");
    setCity("");
    setCanton("");
    setTitle("");
    setAdvertType("");
  }

  function handleSale(e) {
    e.stopPropagation();
    if (token) navigate("add-advert");
    else navigate("login");
  }
  return (
    <div className={styles.hero}>
      <Form onSubmit={handleSubmit} method="post" encType="multipart/form-data">
        <div className={styles.heroImage}>
          <div className={styles.heroText}>
            <h1>
              Nađi savršenu
              <br />
              nekretninu za sebe
            </h1>
            <p>Traži od preko 1000 oglasa</p>
            <div className={styles.heroButtons}>
              <button className={styles.btnPrimary} style={{ display: "flex", flexDirection: "column" }}>
                <div>🔍</div> <div style={{ width: "150px" }}>Traži nekretninu</div>
              </button>
              <button
                type="button"
                className={styles.btnSecondary}
                onClick={(e) => handleSale(e)}
                style={{ display: "flex", flexDirection: "column" }}
              >
                <div>👤</div> <div style={{ width: "150px" }}>Objavi nekretninu</div>
              </button>
            </div>
          </div>
          <div
            className={styles.searchBar}
            style={{ paddingTop: "15px", paddingBottom: "15px" }}
          >
            <div
              style={{
                display: "flex",
                flexDirection: "row",
                alignItems: "flex-end",
                gap: "16px",
                flexWrap: "wrap",
                width: "100%",
              }}
            >
              <label
                htmlFor="title"
                className={styles.searchField}
                style={{ flexGrow: "100" }}
              >
                <label>Title</label>
                <input
                  onChange={(e) => setTitle(e.target.value)}
                  placeholder="..."
                  type="text"
                ></input>
              </label>
              <button
                className={styles.searchBtn}
                style={{ marginLeft: "auto" }}
                type="submit"
              >
                🔍 Search
              </button>
              <button className={styles.clearFiltersBtn} onClick={handleFilterClear}>Clear filters</button>

            </div>
            <div
              style={{
                display: "flex",
                flexDirection: "row",
                alignItems: "flex-end",
                gap: "16px",
                flexWrap: "wrap",
              }}
            >
              <label className={styles.searchField} htmlFor="advertType">
                <label>Tip oglasa</label>
                <select onChange={(e) => setAdvertType(e.target.value)}>
                  <option className={styles.searchField} value="">
                    Oglasi svakog tima
                  </option>
                  <option value="SALE">Prodaja</option>
                  <option value="RENT">Izanjmljivanje</option>
                </select>
              </label>
              <label className={styles.searchField} htmlFor="propertyType">
                <label>Tip nekretnine</label>
                <select onChange={(e) => setPropertyType(e.target.value)}>
                  <option className={styles.searchField} value="">
                    Nekretnina svakog tipa
                  </option>
                  <option value="FLAT">Stan</option>
                  <option value="HOUSE">Kuća</option>
                  <option value="LAND">Zemlja</option>
                  <option value="BUSINESS PLACE">Poslovni prostor</option>
                </select>
              </label>
              <label htmlFor="canton" className={styles.searchField}>
                <select
                  name="canton"
                  placeholder="Canton"
                  onChange={handleCantonChange}
                >
                  <option className={styles.searchField} value="">
                    Svi kantoni
                  </option>
                  ‚
                  {cantons?.map((canton) => (
                    <option
                      style={{ color: "black" }}
                      value={canton.id}
                      key={canton.id}
                    >
                      {canton.name}
                    </option>
                  ))}
                </select>
              </label>
              {canton && (
                <label htmlFor="cityId" className={styles.searchField}>
                  <select
                    name="cityId"
                    placeholder="City"
                    onChange={handleCityChange}
                  >
                    <option className={styles.searchField} value="">
                      Svi gradovi
                    </option>
                    {Object.values(cities).map((city) => (
                      <option
                        style={{ color: "black" }}
                        value={city.id}
                        key={city.id}
                      >
                        {city.name}
                      </option>
                    ))}
                  </select>
                </label>
              )}
              <label htmlFor="minPrice" className={styles.searchField}>
                <label>Min cijena</label>
                <input
                  type="number"
                  onChange={(e) => setMinPrice(e.target.value)}
                  placeholder="KM"
                ></input>
              </label>
              <label htmlFor="maxPrice" className={styles.searchField}>
                <label>Max cijena</label>
                <input
                  type="number"
                  onChange={(e) => setMaxPrice(e.target.value)}
                  placeholder="KM"
                ></input>
              </label>
              <label htmlFor="minSize" className={styles.searchField}>
                <label>Min veličina</label>
                <input
                  type="number"
                  onChange={(e) => setMinSize(e.target.value)}
                  placeholder="m2"
                ></input>
              </label>
              <label htmlFor="maxSize" className={styles.searchField}>
                <label>Max veličina</label>
                <input
                  type="number"
                  onChange={(e) => setMaxSize(e.target.value)}
                  placeholder="m2"
                ></input>
              </label>
            </div>
          </div>
        </div>
      </Form >
    </div >
  );
}
