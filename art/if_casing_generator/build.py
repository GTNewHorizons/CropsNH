import subprocess
import os
from glob import glob
import shutil

FRAME_FULL_FOLDER = os.path.join('.', 'frame_full')
FRAME_OPEN_FOLDER = os.path.join('.', 'frame_open')
TOPS_TEMP_FOLDER = os.path.join('.', 'tops_temp')
OVERLAY_FOLDER = os.path.join('.', 'overlays')
OUTPUT_FOLDER = os.path.join('.','output')

def composite(name, min_index:int, max_index:int, src_folder:str, dest_folder:str|None=None, reverse_order:bool=False):
	overlay_file = os.path.join(OVERLAY_FOLDER, name + ".png")
	if dest_folder is None:
		dest_folder = os.path.join(OUTPUT_FOLDER, name)
	if not os.path.isfile(overlay_file):
		raise "can't find find overlay file"
	if not os.path.isdir(dest_folder):
		os.makedirs(dest_folder)
	
	for file_name in [f'{i}.png' for i in range(min_index, max_index + 1)]:
		src_file = os.path.join(src_folder, file_name)
		dst_file = os.path.join(dest_folder, file_name)
		if not os.path.isfile(src_file):
			raise f"can't fine sides file: {file_name}"
		f1 = src_file
		f2 = overlay_file
		if reverse_order:
			f1, f2 = f2, f1
		subprocess.run([
			'magick', 'convert',
			f1,
			f2,
			'-gravity', 'Center',
			'-composite',
			dst_file
		])
			
	



if __name__ == "__main__":
	if not os.path.isdir(OUTPUT_FOLDER):
		os.makedirs(OUTPUT_FOLDER)
	composite('brick', 2, 13, FRAME_FULL_FOLDER, os.path.join(OUTPUT_FOLDER, 'sides'), reverse_order=True)
	composite('brick', 2, 13, FRAME_OPEN_FOLDER, TOPS_TEMP_FOLDER, reverse_order=True)
	composite('advancedHarvestingUnit', 2, 13, TOPS_TEMP_FOLDER)
	composite('environmentalEnhancementUnit', 2, 13, TOPS_TEMP_FOLDER)
	composite('fertilizerUnit', 2, 13, TOPS_TEMP_FOLDER)
	composite('growthAccelerationUnit', 2, 13, TOPS_TEMP_FOLDER)
	composite('overclockedGrowthAccelerationUnit', 7, 13, TOPS_TEMP_FOLDER)
	shutil.rmtree(TOPS_TEMP_FOLDER)
	shutil.copy(os.path.join(OVERLAY_FOLDER, 'brick.png'), os.path.join(OUTPUT_FOLDER, 'brickedAgriculturalCasing.png'))
	shutil.copy(os.path.join(OVERLAY_FOLDER, 'seedBed.png'), os.path.join(OUTPUT_FOLDER, 'seedBed.png'))