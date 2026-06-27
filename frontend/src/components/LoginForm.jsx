import { Form, useActionData } from "react-router-dom";
import styles from "./css/LoginForm.module.css";

export default function LoginForm() {
  const data = useActionData();
  return (
    <div className={styles.signup}>
      <Form className={styles.form} method="post">
        <p className={styles.title}>Login</p>
        <p className={styles.message}>
          Dobrodošao/la nazad. Ulogiraj se.
        </p>

        <label>
          <input
            required
            placeholder=""
            name="email"
            type="email"
            className={styles.input}
          />
          <span>Email</span>
        </label>

        <label>
          <input
            required
            name="password"
            placeholder=""
            type="password"
            className={styles.input}
          />
          <span>Lozinka</span>
        </label>
        {data?.error && <p className={styles.error}>{data.error}</p>}
        <a href="#" className={styles.forgot}>
          Zaboravljena lozinka?
        </a>

        <button className={styles.submit} type="submit" style={{ width: "100px", height: "40px" }}>
          Ulogiraj se
        </button>
        <p className={styles.signin}>
          Nemaš račun? <a href="/register">Registracija</a>
        </p>
      </Form>
    </div>
  );
}
