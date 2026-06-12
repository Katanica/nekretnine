export function formatDate(isoString) {
    const datum = new Date(isoString);

    const dan = String(datum.getDate()).padStart(2, '0');
    const mjesec = String(datum.getMonth() + 1).padStart(2, '0');
    const godina = datum.getFullYear();

    return `${dan}.${mjesec}.${godina}.`;
}