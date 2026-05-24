import styles from "./css/Btn.module.css";

export default function BtnCategory({ children }) {
  return <button className={styles.filterButton}>{children}</button>;
}
