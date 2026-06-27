import { useNavigate, useRouteError } from "react-router-dom";
import styles from "./css/ErrorPage.module.css";

export default function ErrorPage() {
  const navigate = useNavigate();
  const error = useRouteError();

  return (
    <div className={styles.errorPage}>
      <h1>Error {error?.status}</h1>
      <p>{error?.message || "Nešto je pošlo krivo!"}</p>
      <div className={styles.btns}>
        <button onClick={() => navigate("/")}>Idi na početnu stranicu</button>
        <button onClick={() => navigate(-1)}>Idi nazad</button>
      </div>
    </div>
  );
}
