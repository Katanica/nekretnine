// RegisterForm.jsx
import { useState, useRef, useEffect } from "react";
import styles from "./css/RegisterForm.module.css";
import { Form, useNavigate } from "react-router-dom";
import { uploadImage } from "../uploadImage";

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
  const [cantons, setCantons] = useState([]);
  const [canton, setCanton] = useState(null);
  const [cities, setCities] = useState([]);
  const [city, setCity] = useState(null);
  const noImageUserPath = "https://aurnchyhllskmomhcrxy.supabase.co/storage/v1/object/public/images/no-image-user.png";
  const [avatarPreview, setAvatarPreview] = useState(noImageUserPath); // blob, samo za prikaz
  const [avatarUrl, setAvatarUrl] = useState("");  // pravi URL sa servera
  const fileInputRef = useRef(null);

  async function handleSubmit(e) {
    if (password !== confPassword) {
      e.preventDefault();
      setError("Lozinke se ne podudaraju.");
    }
  }

  async function handleAvatarChange(e) {
    const file = e.target.files[0];
    if (file) {
      setAvatarPreview(URL.createObjectURL(file)); // blob za preview
      const url = await uploadImage(file);
      console.log("RegisterForm url: " + url);       // pravi URL
      setAvatarUrl(url);                           // spremi pravi URL
    }
  }

  const handleCantonChange = (event) => {
    const value = event.target.value;
    setCanton(value);
    if (value) loadCities(value);
  }

  const handleCityChange = (event) => {
    const value = event.target.value;
    setCity(value);
  }

  const loadCities = (id) => {
    async function fetchingData() {
      const resCities = await fetch(`http://localhost:8080/api/city/byCanton/${id}`);

      if (!resCities.ok) {
        setError("Could not fetch data...");
      }

      const citiesData = await resCities.json();
      setCities(citiesData);
      console.log("Mrk", citiesData);
    }
    fetchingData();
  }

  useEffect(() => {
    async function fetchingData() {
      const resCantons = await fetch("http://localhost:8080/api/canton");

      if (!resCantons.ok) {
        setError("Could not fetch data...");
      }

      const cantonsData = await resCantons.json();
      setCantons(cantonsData);
    }
    fetchingData();
  }, []);

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

        <div style={{ display: "flex", flexWrap: "wrap", width: "100%", flexDirection: "row", marginBottom: "20px" }}>

          <div style={{ flexGrow: "1" }}>

          </div>
          <div style={{ flexGrow: "3", paddingTop: "25px" }}>
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
          </div>
          <div style={{ flexGrow: "1" }}>
            <div style={{ display: "flex", justifyContent: "center", marginBottom: "5px" }}>
              <div className={styles.avatarCircle} style={{}}>
                <img src={avatarPreview} style={{}} />
              </div>
            </div>
            <div style={{ display: "flex", justifyContent: "center" }}>
              {avatarPreview !== noImageUserPath ? <button onClick={() => setAvatarPreview(noImageUserPath)} className={styles.avatarUploadBtn} style={{ width: "10%", height: "20%", color: "red" }}>x</button> : <h1></h1>}
              <label htmlFor="avatar-input" className={styles.avatarUploadBtn} style={{ width: "60%", height: "20%" }}>
                Change avatar
              </label>
            </div>
            <input type="hidden" name="avatarUrl" value={avatarUrl} />
            <input
              ref={fileInputRef}
              id="avatar-input"
              type="file"
              accept="image/*"
              style={{ display: "none" }}
              onChange={handleAvatarChange}
            />
          </div>
          <div style={{ flexGrow: "1" }}>

          </div>
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

        <label htmlFor="canton">
          <select
            name="canton"
            placeholder="Canton"
            className={styles.select}
            onChange={handleCantonChange}>
            <option className={styles.option} value="" disabled selected>Canton</option>‚
            {
              cantons?.map((canton) =>
              (<option style={{ color: 'black' }} value={canton.id} key={canton.id}>
                {canton.name}
              </option>))

            }
          </select>
        </label>
        {
          canton && (
            <label htmlFor="cityId">
              <select
                name="cityId"
                placeholder="City"
                className={styles.select}
                onChange={handleCityChange}>
                <option className={styles.option} value="" disabled selected>City</option>
                {
                  Object.values(cities).map((city) =>
                  (<option style={{ color: 'black' }} value={city.id} key={city.id}>
                    {city.name}
                  </option>))

                }
              </select>
            </label>
          )
        }

        {error && <p style={{ color: "red" }}>{error}</p>}

        <button className={styles.submit} type="submit">
          Submit
        </button>
        <p className={styles.signin}>
          Already have an account? <a href="/login">Sign in</a>
        </p>
      </Form >
    </div >
  );
}
