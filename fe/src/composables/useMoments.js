import moment from 'moment';


export function useMoments() {
 




  const formatDate =  (value) => {
    if(value){
        return moment(String(value)).format('DD.MM.YYYY hh:mm')

    }
  }




  return {

    formatDate,
  }
}
