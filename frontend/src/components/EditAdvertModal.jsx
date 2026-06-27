import styles from "./css/EditAdvert.module.css";
import { createPortal } from "react-dom";
import { useState } from "react";
import { Trash2 } from "lucide-react";
import { uploadImage } from "../uploadImage";
import { getToken } from "../api";
export default function EditAdvertModal({ onClose, advert, onUpdated }) {
  const [formData, setFormData] = useState({
    id: advert.id,
    title: advert.title || "",
    description: advert.description || "",
    price: advert.price || "",
    size: advert.size || "",
    advertType: advert.advertType || "SALE",
    propertyType: advert.propertyType || "FLAT",
    cityId: advert.cityId || "",
  });
  const [existingImages, setExistingImages] = useState(advert.imageUrls || []);
  const [newImages, setNewImages] = useState([]); // File objekti
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({ ...prev, [name]: value }));
  };
  const handleFileChange = (e) => {
    setNewImages((prev) => [...prev, ...Array.from(e.target.files)]);
    e.target.value = "";
  };
  const removeExisting = (index) => {
    setExistingImages((prev) => prev.filter((_, i) => i !== index));
  };

  const removeNew = (index) => {
    setNewImages((prev) => prev.filter((_, i) => i !== index));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    setError(null);

    try {
      const newUrls = await Promise.all(
        newImages.map((file) => uploadImage(file)),
      );
      const allImageUrls = [...existingImages, ...newUrls];

      const token = getToken();

      const res = await fetch("http://localhost:8080/api/advert", {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${token}`,
        },
        body: JSON.stringify({ ...formData, imageUrls: allImageUrls }),
      });

      if (!res.ok) throw new Error("Error with updating advert");

      const updated = await res.json();
      onUpdated?.(updated);
      onClose();
    } catch (err) {
      setError(err.message);
    } finally {
      setLoading(false);
    }
  };

  return createPortal(
    <div className={styles.overlay}>
      <div className={styles.modal}>
        <div className={styles.header}>
          <h1>Uredi oglas</h1>
          <button className={styles.btnClose} onClick={onClose}>
            Zatvori
          </button>
        </div>

        <form onSubmit={handleSubmit} className={styles.form}>
          <div className={styles.field}>
            <label>Naslov</label>
            <input
              name="title"
              value={formData.title}
              onChange={handleChange}
              required
            />
          </div>

          <div className={styles.field}>
            <label>Opis</label>
            <textarea
              name="description"
              value={formData.description}
              onChange={handleChange}
              rows={3}
            />
          </div>

          <div className={styles.row}>
            <div className={styles.field}>
              <label>Cijena (KM)</label>
              <input
                name="price"
                type="number"
                value={formData.price}
                onChange={handleChange}
                required
              />
            </div>
            <div className={styles.field}>
              <label>Veličina (m²)</label>
              <input
                name="size"
                type="number"
                value={formData.size}
                onChange={handleChange}
                required
              />
            </div>
          </div>

          <div className={styles.row}>
            <div className={styles.field}>
              <label>Tip oglasa</label>
              <select
                name="advertType"
                value={formData.advertType}
                onChange={handleChange}
              >
                <option value="SALE">Prodaja</option>
                <option value="RENT">Iznajmljivanje</option>
              </select>
            </div>
            <div className={styles.field}>
              <label>Tip nekretnine</label>
              <select
                name="propertyType"
                value={formData.propertyType}
                onChange={handleChange}
              >
                <option value="FLAT">Stan</option>
                <option value="HOUSE">Kuća</option>
                <option value="LAND">Zemlja</option>
              </select>
            </div>
          </div>

          {/* Slike */}
          <div className={styles.field}>
            <label>Slike</label>
            <div className={styles.imageGrid}>
              {existingImages.map((url, i) => (
                <div key={`existing-${i}`} className={styles.imageItem}>
                  <img src={url} alt="" />
                  <button
                    type="button"
                    className={styles.removeImg}
                    onClick={() => removeExisting(i)}
                  >
                    <Trash2 size={14} />
                  </button>
                </div>
              ))}
              {newImages.map((file, i) => (
                <div key={`new-${i}`} className={styles.imageItem}>
                  <img src={URL.createObjectURL(file)} alt="" />
                  <button
                    type="button"
                    className={styles.removeImg}
                    onClick={() => removeNew(i)}
                  >
                    <Trash2 size={14} />
                  </button>
                </div>
              ))}
              <label className={styles.uploadBtn}>
                + Dodaj
                <input
                  type="file"
                  accept="image/*"
                  multiple
                  hidden
                  onChange={handleFileChange}
                />
              </label>
            </div>
          </div>

          {error && <p className={styles.error}>{error}</p>}

          <div className={styles.actions}>
            <button
              type="button"
              onClick={onClose}
              className={styles.btnCancel}
            >
              Nazad
            </button>
            <button type="submit" className={styles.btnSave} disabled={loading}>
              {loading ? "Saving..." : "Save"}
            </button>
          </div>
        </form>
      </div>
    </div>,
    document.body,
  );
}
