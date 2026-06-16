import styles from "./css/DeleteAdvert.module.css";
import { createPortal } from "react-dom";
import { getToken } from "../api";
import { useState } from "react";
import { Form, useParams, useNavigate } from "react-router-dom";
export default function DeleteUserModal({ onClose, id, profileType }) {
    const navigate = useNavigate();
    const token = getToken();
    const [error, setError] = useState(null);
    const [loading, setLoading] = useState(false);
    async function handleDelete() {
        try {
            setLoading(true);
            const response = await fetch(`http://localhost:8080/api/${profileType.toLowerCase()}Profile/${id}`, {
                method: "DELETE",
                headers: {
                    "Content-Type": "application/json",
                    Authorization: `Bearer ${token}`,
                },
            });
            if (!response.ok) throw new Error("Problem with deleting advert");
            else (navigate("/Home"));

            onClose();
        } catch (err) {
            setError(err.message);
        } finally {
            setLoading(false);
        }
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
