import Swal from 'sweetalert2';

export default function swall(title: string, icono: 'success' | 'error' | 'warning' | 'info' | 'question' = 'success') {
    const Toast = Swal.mixin({
        toast: true,
        position: 'top',
        showConfirmButton: false,
        timer: 3000,
        timerProgressBar: true,
        didOpen: (toast) => {
          toast.addEventListener('mouseenter', Swal.stopTimer);
          toast.addEventListener('mouseleave', Swal.resumeTimer);
        }
      });
    Toast.fire({
        icon: icono,
        title: title
    });
      
}