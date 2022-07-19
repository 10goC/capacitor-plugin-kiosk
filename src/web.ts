import { WebPlugin } from '@capacitor/core';

import { KioskModePlugin } from './definitions';

export class KioskModeWeb extends WebPlugin implements KioskModePlugin {
	async enterKioskMode(): Promise<void> {
	}

	async exitKioskMode(): Promise<void> {
	}

	async toggleKioskMode(): Promise<void> {
	}
}
