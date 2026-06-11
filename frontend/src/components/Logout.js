import { redirect } from "react-router-dom";

export function action() {
  localStorage.removeItem("token");
  localStorage.removeItem("expiration");
  localStorage.removeItem("id");
  localStorage.removeItem("role");
  return redirect("/");
}
