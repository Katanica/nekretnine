import styles from "./css/AddAdvertForm.module.css";
import { Form } from "react-router-dom";

export default function AddAdvertForm() {
  return (
    <div className={styles.card}>
      <p className={styles.title}>Dodaj oglas</p>
      <Form method="post" className={styles.form}>
        <div className={styles.row}>
          <div className={styles.group}>
            <label className={styles.label}>Tip nekretnine</label>
            <select name="propertyType" className={styles.input} required>
              <option value="">Odaberi...</option>
              <option value="APARTMENT">Stan</option>
              <option value="HOUSE">Kuća</option>
              <option value="LAND">Zemljište</option>
              <option value="OFFICE">Poslovni prostor</option>
            </select>
          </div>
          <div className={styles.group}>
            <label className={styles.label}>Tip oglasa</label>
            <select name="advertType" className={styles.input} required>
              <option value="">Odaberi...</option>
              <option value="SALE">Prodaja</option>
              <option value="RENT">Iznajmljivanje</option>
            </select>
          </div>
        </div>

        <div className={styles.group}>
          <label className={styles.label}>Naslov</label>
          <input
            name="title"
            type="text"
            className={styles.input}
            placeholder="npr. Trosoban stan u centru"
            required
          />
        </div>

        <div className={styles.group}>
          <label className={styles.label}>Opis</label>
          <textarea
            name="description"
            className={styles.textarea}
            placeholder="Opišite nekretninu..."
            required
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
              required
            />
          </div>
          <div className={styles.group}>
            <label className={styles.label}>Površina (m²)</label>
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

        <button type="submit" className={styles.btn}>
          Objavi oglas
        </button>
      </Form>
    </div>
  );
}
