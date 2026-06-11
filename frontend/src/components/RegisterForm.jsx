// RegisterForm.jsx
import { useState, useRef } from "react";
import styles from "./css/RegisterForm.module.css";
import { Form, useNavigate } from "react-router-dom";

function UserForms() {
  return (
    <div>
      <div className={styles.flex}>
        <label htmlFor="firstName">
          <input
            required
            name="firstName"
            placeholder=""
            type="text"
            className={styles.input}
          />
          <span>First Name</span>
        </label>
        <label htmlFor="lastName">
          <input
            required
            name="lastName"
            placeholder=""
            type="text"
            className={styles.input}
          />
          <span>Last Name</span>
        </label>
        <label htmlFor="dateOfBirth">
          <input
            name="dateOfBirth"
            required
            placeholder=""
            type="date"
            className={styles.input}
          />
          <span>Date of Birth</span>
        </label>
      </div>
    </div>
  );
}

function AgencyForms() {
  return (
    <div className={styles.flex}>
      <label htmlFor="agencyName">
        <input
          required
          name="agencyName"
          placeholder=""
          type="text"
          className={styles.input}
        />
        <span>Agency name</span>
      </label>
      <label htmlFor="oib">
        <input
          required
          name="oib"
          placeholder=""
          type="number"
          className={styles.input}
        />
        <span>OIB</span>
      </label>
    </div>
  );
}

export default function RegisterForm() {
  const navigate = useNavigate();
  const [profileType, setProfileType] = useState("");
  const [password, setPassword] = useState("");
  const [confPassword, setConfPassword] = useState("");
  const [error, setError] = useState("");

  function handleSubmit(e) {
    if (password !== confPassword) {
      e.preventDefault();
      setError("Lozinke se ne podudaraju.");
    }
    navigate("/login");
  }

  return (
    <div className={styles.signup}>
      <Form
        profiletype={profileType}
        className={styles.form}
        onSubmit={handleSubmit}
        method="post"
        encType="multipart/form-data"
      >
        <p className={styles.title}>Register</p>
        <p className={styles.message}>Registruj se i ostvari puni pristup</p>

        <p style={{ margin: 0 }}>Odaberi željeni tip profila</p>
        <div id="radio-holder">
          <input
            type="radio"
            name="profileType"
            value="user"
            checked={profileType === "user"}
            className={styles.radio}
            onChange={() => setProfileType("user")}
          ></input>
          <p className={styles.radioText}>Osobni</p>
          <input
            name="profileType"
            type="radio"
            value="agency"
            checked={profileType === "agency"}
            className={styles.radio}
            onChange={() => setProfileType("agency")}
          ></input>
          <p className={styles.radioText}>Agencija</p>
        </div>

        {profileType === "user" && <UserForms />}
        {profileType === "agency" && <AgencyForms />}

        <label htmlFor="userName">
          <input
            name="userName"
            required
            placeholder=""
            type="text"
            className={styles.input}
          />
          <span>Username</span>
        </label>

        <label htmlFor="email">
          <input
            name="email"
            required
            placeholder=""
            type="email"
            className={styles.input}
          />
          <span>Email</span>
        </label>
        <div className={styles.flex}>
          <label htmlFor="password">
            <input
              name="password"
              required
              placeholder=""
              type="password"
              className={styles.input}
              onChange={(e) => setPassword(e.target.value)}
            />
            <span>Password</span>
          </label>
          <label>
            <input
              required
              placeholder=""
              type="password"
              className={styles.input}
              onChange={(e) => setConfPassword(e.target.value)}
            />
            <span>Confirm Password</span>
          </label>
        </div>
        <label htmlFor="phone">
          <input
            name="phone"
            required
            placeholder=""
            type="tel"
            className={styles.input}
          />
          <span>Phone Number</span>
        </label>

        <label htmlFor="city">
          <input
            name="city"
            placeholder=""
            type="text"
            className={styles.input}
          />
          <span>City</span>
        </label>

        {error && <p style={{ color: "red" }}>{error}</p>}

        <button className={styles.submit} type="submit">
          Submit
        </button>
        <p className={styles.signin}>
          Already have an account? <a href="/login">Sign in</a>
        </p>
      </Form>
    </div>
  );
}
