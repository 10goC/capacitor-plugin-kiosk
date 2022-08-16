import { WebPlugin } from '@capacitor/core';

import { KioskModePlugin, KioskModeStatus } from './definitions';

export class KioskModeWeb extends WebPlugin implements KioskModePlugin {
	async isInKioskMode(): Promise<KioskModeStatus> { return Promise.resolve({ value: true }); }
	async enterKioskMode(): Promise<void> { return Promise.resolve(); }
	async exitKioskMode(): Promise<void> { return Promise.resolve(); }
	async toggleKioskMode(): Promise<void> { return Promise.resolve(); }
}
