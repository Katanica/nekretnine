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
  console.log(`id ${id}, token ${token}`);
  const formData = new FormData();
  formData.append("propertyType", data.get("propertyType"));
  formData.append("advertType", data.get("advertType"));
  formData.append("title", data.get("title"));
  formData.append("description", data.get("description"));
  formData.append("price", data.get("price"));
  formData.append("size", data.get("size"));

  const images = data.getAll("images");
  console.log(images.length);
  images.forEach((img) => {
    console.log(img.name, img.size);
    if (img.size > 0) formData.append("files", img);
  });
  const response = await fetch(
    `http://localhost:8080/api/advert/${id}/with-pictures`,
    {
      method: "POST",
      headers: {
        Authorization: `Bearer ${token}`,
      },
      body: formData,
    },
  );

  if (!response.ok) {
    throw new Response("Problem with adding advert", { status: 500 });
  }

  return redirect("/");
}
