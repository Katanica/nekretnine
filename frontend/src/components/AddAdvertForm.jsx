import styles from "./css/AddAdvertForm.module.css";
import { useSubmit } from "react-router-dom";
import { useState, useRef, useEffect } from "react";
import { uploadImage } from "../uploadImage";

export default function AddAdvertForm() {
  const [previews, setPreviews] = useState([]);
  const [uploading, setUploading] = useState(false);
  const fileInputRef = useRef(null);
  const submit = useSubmit();
  const [cantons, setCantons] = useState([]);
  const [canton, setCanton] = useState(null);
  const [cities, setCities] = useState([]);
  const [city, setCity] = useState(null);

  const handleImageChange = (e) => {
    const selected = Array.from(e.target.files);
    const newPreviews = selected.map((f) => URL.createObjectURL(f));
    setPreviews(newPreviews);
  };

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

  // UCITAVANJE KANTONA
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

  const handleSubmit = async (e) => {
    e.preventDefault();
    const form = e.target;
    setUploading(true);

    try {
      // 1. Uploadaj slike na Supabase
      let imageUrls = [];
      const files = Array.from(fileInputRef.current?.files || []);
      imageUrls = await Promise.all(files.map(uploadImage));

      // 2. Pošalji JSON na action
      const body = {
        propertyType: form.propertyType.value,
        advertType: form.advertType.value,
        title: form.title.value,
        description: form.description.value,
        price: form.price.value,
        size: form.size.value,
        imageUrls: imageUrls,
        cityId: city,
      };

      submit(JSON.stringify(body), {
        method: "post",
        encType: "application/json",
      });
    } catch (err) {
      alert(err.message);
    } finally {
      setUploading(false);
    }
  };

  return (
    <div className={styles.container}>
      <div className={styles.card} style={{}}>
        <p className={styles.title}>Dodaj objavu</p>
        <form className={styles.form} onSubmit={handleSubmit}>
          <div className={styles.row}>
            <div className={styles.group}>
              <label className={styles.label}>Tip nekretnine</label>
              <select name="propertyType" className={styles.input}>
                <option value="">Izaberi...</option>
                <option value="FLAT">Apartman</option>
                <option value="HOUSE">Kuća</option>
                <option value="LAND">Zemlja</option>
                <option value="BUSINESS_PLACE">Poslovni prostor</option>
              </select>
            </div>
            <div className={styles.group}>
              <label className={styles.label}>Tip oglasa</label>
              <select name="advertType" className={styles.input}>
                <option value="">Izaberi...</option>
                <option value="SALE">Prodaja</option>
                <option value="RENTING">Iznajmljivanje</option>
              </select>
            </div>
          </div>

          <div className={styles.group}>
            <label className={styles.label}>Naslov</label>
            <input
              name="title"
              type="text"
              className={styles.input}
              placeholder="npr. Prostran trosoban apartman u centru grada."
            />
          </div>

          <div className={styles.group}>
            <label className={styles.label}>Opis</label>
            <textarea
              name="description"
              className={styles.textarea}
              placeholder="Opiši nekretninu..."
            />
          </div>

          <div className={styles.row}>
            <div className={styles.group}>
              <label className={styles.label}>Cijena (KM)</label>
              <input
                name="price"
                type="number"
                className={styles.input}
                placeholder="0"
                min="0"
              />
            </div>
            <div className={styles.group}>
              <label className={styles.label}>Veličina (m²)</label>
              <input
                name="size"
                type="number"
                className={styles.input}
                placeholder="0"
                min="0"
                step="0.1"
              />
            </div>
          </div>
          <div className={styles.row}>
            <div className={styles.group}>
              <label className={styles.label} >Kanton</label>
              <label htmlFor="canton">
                <select
                  name="canton"
                  placeholder="Canton"
                  className={styles.input}
                  onChange={handleCantonChange}
                >
                  <option className={styles.option} value="" disabled selected>
                    Izaberi kanton
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
            </div>
            {canton && (<div className={styles.group}>
              <label className={styles.label}>Grad</label>
              <label htmlFor="cityId">
                <select
                  className={styles.input}
                  name="cityId"
                  placeholder="City"
                  onChange={handleCityChange}
                >
                  <option className={styles.option} value="" disabled selected>
                    Izaberi grad
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
            </div>
            )}
          </div>
          <div className={styles.group}>
            <label className={styles.label}>Slike</label>
            <input
              ref={fileInputRef}
              type="file"
              accept="image/jpeg,image/png,image/webp"
              multiple
              className={styles.input}
              onChange={handleImageChange}
            />
          </div>

          {previews.length > 0 && (
            <div className={styles.previewGrid}>
              {previews.map((src, i) => (
                <div key={i} className={styles.previewItem}>
                  <img
                    src={src}
                    alt={`preview-${i}`}
                    className={styles.previewImg}
                  />
                </div>
              ))}
            </div>
          )}

          <button type="submit" className={styles.btn} disabled={uploading}>
            {uploading ? "Objavljivanje..." : "Objavi"}
          </button>
        </form>
      </div>
    </div>
  );
}
