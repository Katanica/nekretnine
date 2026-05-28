import { redirect } from "react-router-dom";

const BASE_URL = "http://localhost:8080";

export function getTokenDuration() {
  const storedExpirationDate = localStorage.getItem("expiration");
  const expirationDate = new Date(storedExpirationDate);
  const now = new Date();
  const duration = expirationDate.getTime() - now.getTime();
  return duration;
}

export function getToken() {
  const token = localStorage.getItem("token");

  if (!token) {
    return null;
  }

  const tokenDuration = getTokenDuration();

  if (tokenDuration < 0) {
    return "EXPIRED";
  }

  return token;
}

export function getUserID() {
  const id = localStorage.getItem("id");
  return id;
}

export function tokenLoader() {
  const token = getToken();
  return token;
}

export function checkAuthLoader() {
  const token = getToken();

  if (!token) {
    redirect("/login");
  }
}
