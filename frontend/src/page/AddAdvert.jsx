import styles from "./css/AddAdvert.module.css";
import { Form, redirect } from "react-router-dom";
import { getToken, getUserID } from "../api";
import AddAdvertForm from "../components/AddAdvertForm";

export default function AddAdvert() {
  return <AddAdvertForm />;
}

export async function action({ request }) {
  const data = await request.formData();
  const token = getToken();
  const id = getUserID();
  const advertData = {
    property: data.get("propertyType"),
    advertType: data.get("advertType"),
    title: data.get("title"),
    description: data.get("description"),
    price: data.get("price"),
    size: data.get("size"),
  };
  console.log(token);
  console.log(id);
  const response = await fetch(`http://localhost:8080/api/advert/${id}`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
      Authorization: `Bearer ${token}`,
    },
    body: JSON.stringify(advertData),
  });

  if (!response.ok) {
    throw new Response("Problem with adding advert", { status: 500 });
  }

  return redirect("/");
}
