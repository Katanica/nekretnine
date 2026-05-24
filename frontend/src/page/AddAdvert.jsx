import styles from "./css/AddAdvert.module.css";
import { Form } from "react-router-dom";

export function AddAdvert() {
  return (
    <div>
      <h1 className={styles.pageTitle}>Add Advert</h1>
      <div className={styles.container}>
        <Form method="post" className={styles.form}>
          <ul>
            <li>
              <label htmlFor="title">Title</label>
              <input
                type="text"
                id="title"
                name="title"
                required
                placeholder="e.g. Modern apartment in city center"
              />
            </li>
            <li>
              <label htmlFor="description">Description</label>
              <textarea
                id="description"
                name="description"
                required
                placeholder="Describe the property..."
              />
            </li>
            <li>
              <label htmlFor="price">Price (€)</label>
              <input
                type="number"
                id="price"
                name="price"
                required
                placeholder="245000"
              />
            </li>
            <div className={styles.divider} />
            <button type="submit" className={styles.submitBtn}>
              Submit Advert
            </button>
          </ul>
        </Form>
      </div>
    </div>
  );
}
