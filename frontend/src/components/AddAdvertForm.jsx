import styles from "./css/AddAdvertForm.module.css";
import { useSubmit } from "react-router-dom";
import { useState, useRef } from "react";
import { uploadImage } from "../uploadImage";

export default function AddAdvertForm() {
  const [previews, setPreviews] = useState([]);
  const [uploading, setUploading] = useState(false);
  const fileInputRef = useRef(null);
  const submit = useSubmit();

  const handleImageChange = (e) => {
    const selected = Array.from(e.target.files);
    const newPreviews = selected.map((f) => URL.createObjectURL(f));
    setPreviews(newPreviews);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const form = e.target;
    setUploading(true);

    try {
      // 1. Uploadaj slike na Supabase
      const files = Array.from(fileInputRef.current?.files || []);
      const imageUrls = await Promise.all(files.map(uploadImage));

      console.log("ADD ADVERT FORM " + imageUrls);

      // 2. Pošalji JSON na action
      const body = {
        propertyType: form.propertyType.value,
        advertType: form.advertType.value,
        title: form.title.value,
        description: form.description.value,
        price: form.price.value,
        size: form.size.value,
        imageUrls: imageUrls,
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
    <div className={styles.card}>
      <p className={styles.title}>Add Listing</p>
      <form className={styles.form} onSubmit={handleSubmit}>
        <div className={styles.row}>
          <div className={styles.group}>
            <label className={styles.label}>Property Type</label>
            <select name="propertyType" className={styles.input}>
              <option value="">Select...</option>
              <option value="FLAT">Apartment</option>
              <option value="HOUSE">House</option>
              <option value="LAND">Land</option>
              <option value="BUSINESS_PLACE">Office Space</option>
            </select>
          </div>
          <div className={styles.group}>
            <label className={styles.label}>Listing Type</label>
            <select name="advertType" className={styles.input}>
              <option value="">Select...</option>
              <option value="SALE">Sale</option>
              <option value="RENTING">Rent</option>
            </select>
          </div>
        </div>

        <div className={styles.group}>
          <label className={styles.label}>Title</label>
          <input name="title" type="text" className={styles.input}
            placeholder="e.g. Spacious 3-bedroom apartment in the city center" />
        </div>

        <div className={styles.group}>
          <label className={styles.label}>Description</label>
          <textarea name="description" className={styles.textarea}
            placeholder="Describe the property..." />
        </div>

        <div className={styles.row}>
          <div className={styles.group}>
            <label className={styles.label}>Price (KM)</label>
            <input name="price" type="number" className={styles.input} placeholder="0" min="0" />
          </div>
          <div className={styles.group}>
            <label className={styles.label}>Area (m²)</label>
            <input name="size" type="number" className={styles.input} placeholder="0" min="0" step="0.1" />
          </div>
        </div>

        <div className={styles.group}>
          <label className={styles.label}>Photos</label>
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
                <img src={src} alt={`preview-${i}`} className={styles.previewImg} />
              </div>
            ))}
          </div>
        )}

        <button type="submit" className={styles.btn} disabled={uploading}>
          {uploading ? "Uploading..." : "Publish Listing"}
        </button>
      </form>
    </div>
  );
}