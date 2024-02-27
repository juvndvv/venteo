import { CloudinaryImage } from "@cloudinary/url-gen";
import { thumbnail } from "@cloudinary/url-gen/actions/resize";
import { byRadius } from "@cloudinary/url-gen/actions/roundCorners";

export function getImageUrl(image: string, width: number, rounded: boolean = false): string {
  const myImage = new CloudinaryImage(image, {
    cloudName: "drentd62s",
  }).resize(thumbnail().width(width).height(width));

  if (rounded) {
    myImage.roundCorners(byRadius(60));
  }

  return myImage.toURL();
}

export async function uploadImage(image: File): Promise<any> {
  const cloudname = "drentd62s"
  const url = `https://api.cloudinary.com/v1_1/${cloudname}/image/upload`

  const fd = new FormData()
  fd.append('file', image)
  fd.append('upload_preset', 'yeanjmvq')

  return  fetch(url, {
    method: 'POST',
    body: fd
  })
  .then(response => response.json())
  .then((data): String => {
    console.log('Success:', data.url)
    const regex = /\/([^\/]+)\.\w+$/;
    const match = regex.exec(data.url) || ['', ''];
    return match[1];
  })
}
