
import { useEffect, useState } from "react";

export default function PageBar({ adverts, advertNumber, page, pageSize, onPageChange }) {
    const pageNumbers = Math.ceil(advertNumber / pageSize);

    let advertList = adverts.adverts;
    return (
        <div style={{ bottom: "5px", position: "sticky", display: "flex", flexDirection: "row" }}>
            <div style={{ flexGrow: "10" }}>
            </div>
            <div style={{ flexGrow: "1", textAlign: "center", border: "1px solid lightGray", backgroundColor: "rgb(229, 229, 229)", textAlign: "center", display: "flex", direction: "row", padding: "5px", height: "45px", boxSizing: "border-box", borderRadius: "10px" }}>
                {page !== 0 && (<button onClick={() => { if (page > 0) { onPageChange(page - 1) } }} style={{ flexGrow: "1", border: "1px solid lightGray", height: "100%", maxWidth: "40px", borderRadius: "10px" }}>&lt;</button>)}
                <div style={{ flexGrow: "1", height: "100%", width: "10px", display: "flex", justifyContent: "center", flexDirection: "column" }}><label style={{ fontSize: "10px" }}>Stranica</label>{page + 1}&nbsp;/&nbsp;{pageNumbers}</div>
                {page !== pageNumbers - 1 && (<button onClick={() => { if (page + 1 < pageNumbers) { onPageChange(page + 1) } }} style={{ flexGrow: "1", border: "1px solid lightGray", height: "100%", maxWidth: "40px", borderRadius: "10px" }}>&gt;</button>)}
            </div>
            <div style={{ flexGrow: "10" }}>
            </div>

        </div>
    );
}
