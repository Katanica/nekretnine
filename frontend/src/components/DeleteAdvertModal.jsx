import styles from "./css/DeleteAdvert.module.css";
import { createPortal } from "react-dom";
import { getToken } from "../api";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
export default function DeleteAdvertModal({ onClose, id }) {
  const token = getToken();
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();
  async function handleDelete() {
    e.preventDefault();
    try {
      setLoading(true);
      const response = await fetch(`http://localhost:8080/api/advert/${id}`, {
        method: "DELETE",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${token}`,
        },
      });
      if (!response.ok) throw new Error("Problem with deleting advert");



      onClose();
    } catch (err) {
      setError(err.message);
    } finally {
      setLoading(false);
    }
    navigate(`/home`);

  }
  return createPortal(
    <div className={styles.overlay}>
      <div className={styles.modal}>
        <h3>Are you sure?</h3>

        <div className={styles.btn}>
          <button
            onClick={handleDelete}
            className={styles.btnDelete}
            disabled={loading}
          >
            {loading ? "Deleting" : "Delete"}
          </button>
          <button onClick={onClose}>Cancel</button>
        </div>
      </div>
    </div>,
    document.body,
  );
}
