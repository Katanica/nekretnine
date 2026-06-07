import { createPortal } from "react-dom";
import styles from "./css/EditProfile.module.css";
import { useState } from "react";
import { getToken, getUserID } from "../api";
export default function EditProfileModal({
  onClose,
  email,
  userName,
  name,
  surname,
}) {
  const token = getToken();
  const userID = getUserID();
  const [avatar, setAvatar] = useState(null);
  const [avatarPreview, setAvatarPreview] = useState(null);
  const [error, setError] = useState(null);
  const [passwordError, setPasswordError] = useState("");
  const [form, setForm] = useState({
    name: "",
    surname: "",
    phone: "",
    password: "",
    confirmPassword: "",
  });

  const handleAvatarChange = (e) => {
    const file = e.target.files[0];
    if (file) {
      setAvatar(file);
      setAvatarPreview(URL.createObjectURL(file));
    }
  };

  const handleChange = (e) => {
    setForm((prev) => ({ ...prev, [e.target.name]: e.target.value }));
    setPasswordError("");
  };

  async function handleSubmit() {
    // Validate password match if password is not empty
    if (form.password && form.password !== form.confirmPassword) {
      setPasswordError("Passwords do not match");
      return;
    }

    const formData = new FormData();

    const userProfileData = {
      id: userID,
      name: form.name,
      surname: form.surname,
      userName: userName,
      email: email,
      phone: form.phone,
      passwordHash: form.password,
    };
    formData.append(
      "userProfile",
      new Blob([JSON.stringify(userProfileData)], {
        type: "application/json",
      }),
    );
    if (avatar) formData.append("avatar", avatar);
    const response = await fetch(
      "http://localhost:8080/api/userProfile/with-avatar",
      {
        method: "PUT",
        headers: {
          Authorization: `Bearer ${token}`,
        },
        body: formData,
      },
    );

    if (!response.ok) {
      setError("Problem with saving changes");
    }
    onClose();
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
            {avatarPreview ? (
              <img src={avatarPreview} alt="avatar" />
            ) : (
              <span>MK</span>
            )}
          </div>
          <label htmlFor="avatar-input" className={styles.avatarUploadBtn}>
            Change avatar
          </label>
          <input
            id="avatar-input"
            type="file"
            accept="image/*"
            style={{ display: "none" }}
            onChange={handleAvatarChange}
          />
          <div className={styles.formRow}>
            <div className={styles.formGroup}>
              <label>First name</label>
              <input name="name" value={form.name} onChange={handleChange} />
            </div>
            <div className={styles.formGroup}>
              <label>Surname</label>
              <input
                name="surname"
                value={form.surname}
                onChange={handleChange}
              />
            </div>
          </div>

          <div className={styles.formRow}>
            <div className={styles.formGroup}>
              <label>Username</label>
              <input name="username" value={userName} readOnly />
            </div>
            <div className={styles.formGroup}>
              <label>Email</label>
              <input name="email" value={email} readOnly />
            </div>
          </div>

          <div className={styles.formGroup}>
            <label>Phone number</label>
            <input
              name="phone"
              type="tel"
              value={form.phone}
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
            <button className={styles.btnSave} onClick={handleSubmit}>
              Save
            </button>
          </div>
        </div>
      </div>
    </div>,
    document.body,
  );
}
