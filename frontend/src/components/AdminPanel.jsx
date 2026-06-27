import { useEffect, useState } from "react";
import { getToken } from "../api";
import { formatDate } from "../formatDate";
import styles from "./css/AdminPanel.module.css";

export default function AdminPanel() {
  const token = getToken();
  const [profiles, setProfiles] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    async function fetchProfiles() {
      try {
        const res = await fetch("http://localhost:8080/api/profile", {
          headers: { Authorization: `Bearer ${token}` },
        });
        if (!res.ok) throw new Error("Failed to fetch profiles");
        const data = await res.json();
        setProfiles(data);
      } catch (err) {
        setError(err.message);
      } finally {
        setLoading(false);
      }
    }

    fetchProfiles();
  }, []);

  async function handleDelete(id) {
    try {
      const res = await fetch(`http://localhost:8080/api/profile/${id}`, {
        method: "DELETE",
        headers: { Authorization: `Bearer ${token}` },
      });
      if (!res.ok) throw new Error("Failed to delete profile");
      setProfiles((prev) => prev.filter((p) => p.id !== id));
    } catch (err) {
      setError(err.message);
    }
  }

  if (loading) return <div className={styles.status}>Učitavanje profila...</div>;
  if (error) return <div className={styles.error}>{error}</div>;

  return (
    <div className={styles.container}>
      <h2 className={styles.title}>Admin Panel — Svi profili</h2>
      <div className={styles.tableWrapper}>
        <table className={styles.table}>
          <thead>
            <tr>
              <th>ID</th>
              <th>Username</th>
              <th>Email</th>
              <th>Telefon</th>
              <th>Tip profila</th>
              <th>Role</th>
              <th>Napravljen datuma:</th>
              <th></th>
            </tr>
          </thead>
          <tbody>
            {profiles.map((profile) => (
              <tr key={profile.id}>
                <td>{profile.id}</td>
                <td>{profile.userName}</td>
                <td>{profile.email}</td>
                <td>{profile.phone}</td>
                <td>{profile.profileType}</td>
                <td>{profile.role}</td>
                <td>{profile.createdAt ? formatDate(profile.createdAt) : "—"}</td>
                <td>
                  <button
                    className={styles.deleteBtn}
                    onClick={() => handleDelete(profile.id)}
                  >
                    Obriši
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}
