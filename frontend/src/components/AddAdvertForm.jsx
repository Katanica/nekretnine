import styles from "./css/AddAdvertForm.module.css";
import { useSubmit } from "react-router-dom";
import { useState, useRef } from "react";

export default function AddAdvertForm() {
  const [previews, setPreviews] = useState([]);
  const fileInputRef = useRef(null);
  const submit = useSubmit();

  const handleImageChange = (e) => {
    const selected = Array.from(e.target.files);
    const newPreviews = selected.map((f) => URL.createObjectURL(f));
    setPreviews((prev) => [...prev, ...newPreviews]);
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    const form = e.target;
    const formData = new FormData();

    formData.append("propertyType", form.propertyType.value);
    formData.append("advertType", form.advertType.value);
    formData.append("title", form.title.value);
    formData.append("description", form.description.value);
    formData.append("price", form.price.value);
    formData.append("size", form.size.value);

    const files = fileInputRef.current?.files;
    if (files) {
      Array.from(files).forEach((file) => {
        formData.append("images", file);
      });
    }

    submit(formData, { method: "post", encType: "multipart/form-data" });
  };

  return (
    <div className={styles.card}>
      <p className={styles.title}>Add Listing</p>
      <form className={styles.form} onSubmit={handleSubmit}>
        <div className={styles.row}>
          <div className={styles.group}>
            <label className={styles.label}>Property Type</label>
            <select name="propertyType" className={styles.input} required>
              <option value="">Select...</option>
              <option value="FLAT">Apartment</option>
              <option value="HOUSE">House</option>
              <option value="LAND">Land</option>
              <option value="BUSINESS_PLACE">Office Space</option>
            </select>
          </div>
          <div className={styles.group}>
            <label className={styles.label}>Listing Type</label>
            <select name="advertType" className={styles.input} required>
              <option value="">Select...</option>
              <option value="SALE">Sale</option>
              <option value="RENTING">Rent</option>
            </select>
          </div>
        </div>

        <div className={styles.group}>
          <label className={styles.label}>Title</label>
          <input
            name="title"
            type="text"
            className={styles.input}
            placeholder="e.g. Spacious 3-bedroom apartment in the city center"
            required
          />
        </div>

        <div className={styles.group}>
          <label className={styles.label}>Description</label>
          <textarea
            name="description"
            className={styles.textarea}
            placeholder="Describe the property..."
            required
          />
        </div>

        <div className={styles.row}>
          <div className={styles.group}>
            <label className={styles.label}>Price (KM)</label>
            <input
              name="price"
              type="number"
              className={styles.input}
              placeholder="0"
              min="0"
              required
            />
          </div>
          <div className={styles.group}>
            <label className={styles.label}>Area (m²)</label>
            <input
              name="size"
              type="number"
              className={styles.input}
              placeholder="0"
              min="0"
              step="0.1"
              required
            />
          </div>
        </div>

        <div className={styles.group}>
          <label className={styles.label}>Photos</label>
          <input
            ref={fileInputRef}
            type="file"
            accept="image/*"
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

        <button type="submit" className={styles.btn}>
          Publish Listing
        </button>
      </form>
    </div>
  );
}
