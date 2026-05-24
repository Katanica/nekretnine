import { Form } from "react-router-dom";
import styles from "./css/LoginForm.module.css";

export default function LoginForm() {
  return (
    <div className={styles.signup}>
      <Form className={styles.form} method="post">
        <p className={styles.title}>Login</p>
        <p className={styles.message}>
          Welcome back! Please login to your account.
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
          <span>Password</span>
        </label>

        <a href="#" className={styles.forgot}>
          Forgot password?
        </a>

        <button className={styles.submit} type="submit">
          Login
        </button>
        <p className={styles.signin}>
          Don't have an account? <a href="/register">Register</a>
        </p>
      </Form>
    </div>
  );
}
