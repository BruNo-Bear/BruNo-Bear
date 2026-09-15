# Bruce Massingue — Portfolio Site

A self-contained static website (HTML/CSS/JS — no build step, no server required).

## Folder contents

```
index.html            The whole site (one page, anchor-linked sections)
css/style.css          Styling
js/main.js              Mobile nav, gallery filtering, lightbox
assets/img/             Photos, logo, favicons
assets/files/           Downloadable CV (linked from the "Download CV" button)
```

## Preview it locally

Double-click `index.html`, or from a terminal in this folder run:

```
python3 -m http.server 8000
```

then open `http://localhost:8000` in a browser.

## Put it on your own domain

Any of these work — pick whichever is easiest for you:

### Option A — Netlify (free, easiest, no account needed to start)
1. Go to https://app.netlify.com/drop
2. Drag this whole folder onto the page.
3. Netlify gives you a live `*.netlify.app` link immediately.
4. To use your own domain: Netlify dashboard → *Domain settings* → *Add a domain* → follow the DNS instructions it gives you (usually one CNAME or A record at your domain registrar, e.g. where you bought the domain).

### Option B — GitHub Pages (free)
1. Create a new GitHub repository and upload everything in this folder to it.
2. Repo → *Settings* → *Pages* → set the source branch to `main` (root).
3. GitHub gives you a `yourname.github.io` link.
4. For a custom domain: add a `CNAME` file containing your domain name to the repo root, then add the DNS records GitHub's Pages settings page shows you.

### Option C — Your own web host (cPanel / existing hosting)
1. Log in to your host's file manager (or connect with an FTP client like FileZilla).
2. Upload everything in this folder into `public_html` (or `www`, depending on your host).
3. Make sure `index.html` sits at the top level of that folder — not inside an extra subfolder.
4. If your domain isn't already pointed at this host, update its nameservers or DNS `A`/`CNAME` records to your host's values (your host's support page will have the exact values).

### Buying a domain
If you don't have a domain yet, South African options include Domains.co.za, Afrihost, and Xneelo; international options include Namecheap and Google Domains. A `.co.za` or `.com` with your name (e.g. `brucemassingue.co.za`) works well for this kind of site.

## Updating content later
- **Photos**: replace files in `assets/img/` (keep the same filenames, or update the paths in `index.html`).
- **Text**: edit directly in `index.html` — each section is clearly commented (`<!-- ABOUT -->`, `<!-- WORK / GALLERY -->`, etc.).
- **CV download**: replace `assets/files/Bruce_Massingue_CV.docx` with an updated version, keeping the same filename.
