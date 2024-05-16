package entities;

public class Admin {
	//cette class qui va envoyer les notification 
	
		//tout les admins sont des admin normale sauf un super admin qui controle la totalite de l'application 
		private String nature;

		public Admin(String nature) {
			super();
			this.nature = nature;
		}

		public String getNature() {
			return nature;
		}

		public void setNature(String nature) {
			this.nature = nature;
		}
}
