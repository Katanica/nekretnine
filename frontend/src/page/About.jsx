import styles from "./css/About.module.css";

const team = [
    {
        name: "Mario Ljušanin",
        role: "Suosnivač & Developer",
        bio: "Više od 10 godina iskustva u prometu nekretninama. Pokrenuo platformu s vizijom da kupovina stana bude jednostavna kao online kupovina.",
        initials: `MLJ`,
    },
    {
        name: "Jurica Stjepanović",
        role: "Suosnivač & Developer",
        bio: "Specijaliziran za stambene nekretnine. Osigurava da svaki oglas bude precizan, jasan i privlačan.",
        initials: "JS",
    },
    {
        name: "Gabrijel Katana",
        role: "Suosnivač & Developer",
        bio: "Gradi platformu iznutra. Brine o tome da pretraživanje, filtriranje i kontakt funkcioniraju bez trzavica.",
        initials: "GK",
    },
];

const values = [
    {
        label: "Transparentnost",
        description:
            "Svaki oglas prikazuje realne informacije — bez skrivenih troškova, bez nejasnih cijena.",
    },
    {
        label: "Jednostavnost",
        description:
            "Kupovina ili prodaja nekretnine ne treba biti komplicirana. Dizajnirali smo svaki korak s tim na umu.",
    },
    {
        label: "Povjerenje",
        description:
            "Verificiramo oglašivače i potičemo direktnu komunikaciju između kupaca i prodavača.",
    },
];

export default function About() {
    return (
        <main className={styles.page}>
            {/* Hero */}
            <section className={styles.hero}>
                <p className={styles.eyebrow}>O nama</p>
                <h1 className={styles.heroTitle}>
                    Tržište nekretnina —<br />
                    <span className={styles.accent}>bez posrednika</span>
                </h1>
                <p className={styles.heroSub}>
                    Povezujemo ljude koji traže dom s onima koji ga prodaju. Direktno,
                    pošteno i brzo.
                </p>
            </section>

            <div className={styles.divider} />

            {/* Misija i vrijednosti */}
            <section className={styles.section}>
                <h2 className={styles.sectionTitle}>Što nas pokreće</h2>
                <div className={styles.valuesGrid}>
                    {values.map(({ label, description }) => (
                        <div key={label} className={styles.valueCard}>
                            <span className={styles.valueDot} />
                            <h3 className={styles.valueLabel}>{label}</h3>
                            <p className={styles.valueDesc}>{description}</p>
                        </div>
                    ))}
                </div>
            </section>

            <div className={styles.divider} />

            {/* Tim */}
            <section className={styles.section}>
                <h2 className={styles.sectionTitle}>Tim</h2>
                <div className={styles.teamGrid}>
                    {team.map(({ name, role, bio, initials }) => (
                        <div key={name} className={styles.teamCard}>
                            <div className={styles.avatar}>{initials}</div>
                            <div className={styles.teamInfo}>
                                <p className={styles.teamName}>{name}</p>
                                <p className={styles.teamRole}>{role}</p>
                            </div>
                        </div>
                    ))}
                </div>
            </section>
        </main>
    );
}