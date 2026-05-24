import styles from "./css/CategoryFilter.module.css";
import Button from "./BtnCategory";
import stanImg from "../assets/Stan.png";
import kucaImg from "../assets/Kuca.png";
import kljucImg from "../assets/Kljuc.png";
import zemljisteImg from "../assets/Prostor.png";
const categories = [
  { label: "Stanovi", img: stanImg },
  { label: "Kuće", img: kucaImg },
  { label: "Poslovni prostori", img: kljucImg },
  { label: "Zemljišta", img: zemljisteImg },
];
export default function CategoryFilter() {
  return (
    <div className={styles.boxContent}>
      {categories.map((cat) => (
        <Button key={cat.label}>
          <img src={cat.img} alt={cat.label} className={styles.img} />
          <span className={styles.span}>{cat.label}</span>
        </Button>
      ))}
    </div>
  );
}
