import { ref } from 'vue'
import axios from 'axios'

export function useMessages() {

  const lowProgress = [
    "Der erste Schritt in ein neues Abenteuer! Zeit, das Buch aufzuschlagen.",
    "Jedes Buch beginnt mit einer Seite. Seid ihr bereit für die Reise?",
    "Es ist noch kein Meister vom Himmel gefallen, aber das Buch wartet auf dich!"
  ];
  
  const firstProgress = [
    "Ein Viertel geschafft! Die Geschichte nimmt Fahrt auf.",
    "Jedes Buch beginnt mit einer Seite. Seid ihr bereit für die Reise?",
    "Das Eis ist gebrochen! Es gibt noch viel zu entdecken."
  ];
  
  const mediumProgress = [
    "Halbzeit! Die Spannung steigt. Was denkst du bisher?",
    "Auf halbem Weg! Der Plot verdichtet sich, bleib dran.",
    "Ihr seid auf der Hälfte angekommen! Der Höhepunkt rückt näher."
  ];
  
  const advancedProgress = [
    "Nur noch ein kleines Stück! Wie wird es wohl enden?",
    "Fast geschafft! Die letzten Kapitel sind immer die spannendsten.",
    "Das Ziel ist in Sichtweite! Noch ein paar Seiten und ihr seid durch."
  ];
  
  const getProgressMessage = (progress) => {
    if (progress < 25) {
      return lowProgress[Math.floor(Math.random() * lowProgress.length)];
    } else if (progress < 50) {
      return firstProgress[Math.floor(Math.random() * firstProgress.length)];
    } else if (progress < 75) {
      return mediumProgress[Math.floor(Math.random() * mediumProgress.length)];
    } else if (progress < 100) {
      return advancedProgress[Math.floor(Math.random() * advancedProgress.length)];
    } else {
      return "Herzlichen Glückwunsch! Ihr habt es geschafft.";
    }
  };

  return {
    
    getProgressMessage,
  }
}
