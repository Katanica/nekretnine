import { createPortal } from "react-dom";
import styles from "./css/EditProfile.module.css";
import { useState, useRef, useEffect } from "react";
import { getToken, getUserID, getProfileType } from "../api";
import { uploadImage } from "../uploadImage";
import { useNavigate } from "react-router-dom";
export default function EditProfileModal({ onClose, user }) {
  const token = getToken();
  const userID = getUserID();
  const profileType = getProfileType();
  const fileInputRef = useRef(null);
  const [avatar, setAvatar] = useState("");
  const [avatarPreview, setAvatarPreview] = useState(user.avatarUrl);
  const [error, setError] = useState(null);
  const [passwordError, setPasswordError] = useState("");
  const [form, setForm] = useState({
    name: user?.name || "",
    surname: user?.surname || "",
    phone: user?.phone || "",
    cantonId: user?.city.canton.id || "",
    cityId: user?.city.id || "",
    password: user?.password || "",
    confirmPassword: "",
    oib: user?.oib || "",
    agencyName: user?.agencyName || "",
  });

  const navigate = useNavigate();

  const [cantons, setCantons] = useState([]);
  const [canton, setCanton] = useState(null);
  const [cities, setCities] = useState([]);
  const [city, setCity] = useState(null);

  const handleCantonChange = (event) => {
    const value = event.target.value;
    setForm((prev) => ({ ...prev, cantonId: value, cityId: "" })); // resetuj grad pri promjeni kantona
    if (value) loadCities(value);
  }

  const handleCityChange = (event) => {
    const value = event.target.value;
    setForm((prev) => ({ ...prev, cityId: value }));
  }

  const loadCities = (id) => {
    async function fetchingData() {
      const resCities = await fetch(`http://localhost:8080/api/city/byCanton/${id}`);

      if (!resCities.ok) {
        setError("Could not fetch data...");
      }

      const citiesData = await resCities.json();
      setCities(citiesData);
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

  const handleAvatarChange = (e) => {
    const file = e.target.files[0];
    if (file) {
      setAvatar(file);
      setAvatarPreview(URL.createObjectURL(file));
    }
  };

  useEffect(() => {
    if (user.city.canton.id)
      loadCities(user.city.canton.id);
  }, [canton]);

  const handleChange = (e) => {
    setForm((prev) => ({ ...prev, [e.target.name]: e.target.value }));
    setPasswordError("");
  };

  async function handleSubmit(e) {
    e.preventDefault();
    // Validate password match if password is not empty
    if (form.password && form.password !== form.confirmPassword) {
      setPasswordError("Passwords do not match");
      return;
    }

    let imageUrl = "";
    if (fileInputRef.current?.files[0]) {
      const file = fileInputRef.current?.files[0] || "";
      imageUrl = await uploadImage(file);
    }

    setAvatar(imageUrl);

    const userData = profileType.toUpperCase() === "USER" ? {
      id: userID,
      name: form.name,
      surname: form.surname,
      userName: user?.userName || "",
      email: user?.email || "",
      phone: form.phone,
      cityId: form.cityId,
      password: form.password,
      avatarUrl: imageUrl !== "" ? imageUrl : user.avatarUrl
    } :
      {
        id: userID,
        userName: user?.userName || "",
        email: user?.email || "",
        phone: form.phone,
        cityId: form.cityId,
        password: form.password,
        avatarUrl: imageUrl !== "" ? imageUrl : user.avatarUrl,
        oib: form.oib,
        agencyName: form.agencyName
      };

    console.log("Šaljem:", JSON.stringify(userData, null, 2)); // ← dodaj ovo

    const response = await fetch(
      `http://localhost:8080/api/${profileType.toLowerCase()}Profile`,
      {
        method: "PUT",
        headers: {
          Authorization: `Bearer ${token}`,
          "Content-Type": "application/json"
        },
        body: JSON.stringify(userData),
      },
    );

    if (!form.cityId) return;
    if (!response.ok) {
      setError("Problem with saving changes");
      return;
    }
    window.location.href = `/profile/${user.id}`;
    window.location.reload();
  }

  if (error) return <div>{error}</div>;

  return createPortal(
    <div className={styles.overlay}>
      <div className={styles.modal}>
        <div className={styles.header}>
          <h2>Edit profile</h2>
          <button onClick={onClose}>Close</button>
        </div>
        <div className={styles.avatarSection}>
          <div className={styles.avatarCircle}>
            <img src={avatarPreview} />
          </div>
          <label htmlFor="avatar-input" className={styles.avatarUploadBtn}>
            Change avatar
          </label>
          <input
            ref={fileInputRef}
            id="avatar-input"
            type="file"
            accept="image/*"
            style={{ display: "none" }}
            onChange={handleAvatarChange}
          />
          {user.profileType === "USER" ?
            <div className={styles.formRow}>
              <div className={styles.formGroup}>
                <label>First name</label>
                <input
                  name="name"
                  value={form.name || ""}
                  onChange={handleChange}
                />
              </div>
              <div className={styles.formGroup}>
                <label>Surname</label>
                <input
                  name="surname"
                  value={form.surname || ""}
                  onChange={handleChange}
                />
              </div>
            </div>
            :
            <div className={styles.formRow}>
              <div className={styles.formGroup}>
                <label>Agency name</label>
                <input
                  name="agencyName"
                  value={form.agencyName || ""}
                  onChange={handleChange}
                />
              </div>
              <div className={styles.formGroup}>
                <label>OIB</label>
                <input
                  name="oib"
                  value={form.oib || ""}
                  onChange={handleChange}
                />
              </div>
            </div>

          }
          <div className={styles.formRow}>
            <div className={styles.formGroup}>
              <label>Username</label>
              <input name="username" value={user.userName || ""} readOnly />
            </div>
            <div className={styles.formGroup}>
              <label>Email</label>
              <input name="email" value={user.email || ""} readOnly />
            </div>
          </div>

          <div className={styles.formRow}>

            <div className={styles.formGroup}>
              <label htmlFor="canton">
                Canton
              </label>
              <select
                name="canton"
                placeholder="Canton"
                value={form.cantonId}
                onChange={handleCantonChange}>
                <option value="" disabled selected>Canton</option>‚
                {
                  cantons?.map((canton) =>
                  (<option style={{ color: 'black' }} value={canton.id} key={canton.id}>
                    {canton.name}
                  </option>))

                }
              </select>
            </div>
            <div className={styles.formGroup}>
              <label htmlFor="cityId" className={styles.formGroup}>
                City
              </label>
              <select
                name="cityId"
                placeholder="City"
                value={form.cityId}
                onChange={handleCityChange}>
                <option className={styles.option} value="" disabled selected>City</option>
                {
                  Object.values(cities).map((city) =>
                  (<option style={{ color: 'black' }} value={city.id} key={city.id}>
                    {city.name}
                  </option>))

                }
              </select>

            </div>
          </div>

          <div className={styles.formGroup}>
            <label>Phone number</label>
            <input
              name="phone"
              type="tel"
              value={form.phone || ""}
              onChange={handleChange}
            />
          </div>

          <div className={styles.formRow}>
            <div className={styles.formGroup}>
              <label>New password</label>
              <input
                name="password"
                type="password"
                value={form.password}
                onChange={handleChange}
              />
            </div>
            <div className={styles.formGroup}>
              <label>Confirm new password</label>
              <input
                name="confirmPassword"
                type="password"
                value={form.confirmPassword}
                onChange={handleChange}
              />
              {passwordError && (
                <span className={styles.errorMessage}>{passwordError}</span>
              )}
            </div>
          </div>

          <div className={styles.formActions}>
            <button className={styles.btnCancel} onClick={onClose}>
              Cancel
            </button>
            <button className={styles.btnSave} onClick={(e) => handleSubmit(e)} type="button">
              Save
            </button>
          </div>
        </div>
      </div>
    </div >,
    document.body,
  );
}
